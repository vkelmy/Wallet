package com.vk.wallet.feature_wallet.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.wallet.feature_wallet.domain.model.Transaction
import com.vk.wallet.feature_wallet.domain.usecase.GetDateUseCase
import com.vk.wallet.feature_wallet.domain.usecase.GetFormattedDateUseCase
import com.vk.wallet.feature_wallet.domain.usecase.database.DatabaseUseCases
import com.vk.wallet.feature_wallet.domain.usecase.datastore.DatastoreUseCases
import com.vk.wallet.feature_wallet.presentation.util.AccountsType
import com.vk.wallet.feature_wallet.presentation.util.Categories
import com.vk.wallet.feature_wallet.presentation.util.TabButton
import com.vk.wallet.feature_wallet.presentation.util.TransactionType
import com.vk.wallet.feature_wallet.presentation.util.amountFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDateUseCase: GetDateUseCase,
    private val getFormattedDateUseCase: GetFormattedDateUseCase,
    private val getDatabaseUseCases: DatabaseUseCases,
    private val getDatastoreUseCases: DatastoreUseCases
): ViewModel() {
    private var decimal: String = String()
    private var isDecimal = MutableStateFlow(false)
    private var duration = MutableStateFlow(0)

    private var _tabButton = MutableStateFlow(TabButton.TODAY)
    val tabButton = _tabButton

    private var _category = MutableStateFlow(Categories.SALARY)
    val category = _category

    private var _account = MutableStateFlow(AccountsType.CASH)
    val account = _account

    private var _transactionAmount = MutableStateFlow("0.00")
    val transactionAmount = _transactionAmount

    private var _dailyTransaction = MutableStateFlow(emptyList<Transaction>())
    val dailyTransaction = _dailyTransaction

    private var _monthlyTransaction = MutableStateFlow(mapOf<String, List<Transaction>>())
    val monthlyTransaction = _monthlyTransaction

    private var _currentExpenseAmount = MutableStateFlow(0.0)
    val currentExpenseAmount = _currentExpenseAmount

    private var _transactionTitle = MutableStateFlow(String())
    val transactionTitle = _transactionTitle

    private var _showInfoBanner = MutableStateFlow(false)
    val showInfoBanner = _showInfoBanner

    private var _totalIncome = MutableStateFlow(0.0)
    val totalIncome = _totalIncome

    private var _totalExpense = MutableStateFlow(0.0)
    val totalExpense = _totalExpense

    private var _formattedDate = MutableStateFlow(String())
    val formattedDate = _formattedDate

    private var _date = MutableStateFlow(String())
    val date = _date

    private var _currentTime = MutableStateFlow(Calendar.getInstance().time)
    val currentTime = _currentTime

    private var _selectedCurrencyCode = MutableStateFlow(String())
    val selectedCurrencyCode = _selectedCurrencyCode

    private var _limitAlert = MutableSharedFlow<HomeUIEvent>(replay = 1)
    val limitAlert = _limitAlert

    private var _limitKey = MutableStateFlow(false)
    val limitKey = _limitKey


    init {
        val currentDate = getDateUseCase()
        _formattedDate.value = getFormattedDateUseCase(_currentTime.value)
        _date.value = currentDate

        currencyFormat()

        viewModelScope.launch(IO) {
            getDatastoreUseCases.getLimitDurationUseCase().collect { pref ->
                duration.value = pref
            }
        }

        viewModelScope.launch(IO) {
            getDatastoreUseCases.getLimitKeyUseCase().collectLatest { pref ->
                _limitKey.value = pref
            }
        }

        viewModelScope.launch(IO) {
            when (duration.value) {
                0 -> {
                    getDatabaseUseCases.getCurrentDayExpTransactionUseCase().collect { result ->
                        _currentExpenseAmount.value = calculateTransaction(result.map { it.amount })
                    }
                }
                1 -> {
                    getDatabaseUseCases.getWeeklyExpTransactionUseCase().collect { result ->
                        _currentExpenseAmount.value = calculateTransaction(result.map { it.amount })
                    }
                }
                else -> {
                    getDatabaseUseCases.getMonthlyExpTransactionUse().collect { result ->
                        _currentExpenseAmount.value = calculateTransaction(result.map { it.amount })
                    }
                }
            }
        }

        viewModelScope.launch(IO) {
            getDatabaseUseCases.getDailyTransactionUseCase(currentDate).collect {
                it?.let { expenses ->
                    _dailyTransaction.value = expenses.reversed()
                }
            }
        }

        viewModelScope.launch(IO) {
            getDatabaseUseCases.getAllTransactionsUseCase().collect {allTransaction ->
                allTransaction?.let {
                    _monthlyTransaction.value = it.reversed().groupBy { monthlyExpense ->
                        getFormattedDateUseCase(monthlyExpense.date)
                    }
                }
            }
        }

        viewModelScope.launch(IO) {
            getDatabaseUseCases.getAccountsUseCase().collect { accounts ->
                val income = calculateTransaction(accounts.map { it.income })
                val expense = calculateTransaction(accounts.map { it.expense })

                _totalIncome.value = income
                _totalExpense.value = expense
            }
        }

    }


    private fun calculateTransaction(transactions: List<Double>): Double {
        return transactions.sumOf { it }
    }

    fun selectTabButton(button: TabButton) {
        _tabButton.value = button
    }

    fun selectCategory(category: Categories) {
        this._category.value = category
    }

    fun selectAccount(accountType: AccountsType) {
        this._account.value = accountType
    }

    fun setTransactionTitle(title: String) {
        _transactionTitle.value = title
    }

    fun setCurrentTime(time: Date) {
        _currentTime.value = time
    }

    fun insertDailyTransaction(
        date: String,
        amount: Double,
        category: String,
        transactionType: String,
        transactionTitle: String,
        navigateBack: () -> Unit
    ) {
        viewModelScope.launch(IO) {
            if (amount <= 0.0) {
                _showInfoBanner.value = true
                delay(2000)
                _showInfoBanner.value = false
                return@launch
            }

            val newTransaction = Transaction(
                _currentTime.value,
                date,
                amount,
                _account.value.title,
                category,
                transactionType,
                transactionTitle
            )
            getDatabaseUseCases.insertNewTransactionUseCase(newTransaction)

            if (transactionType == TransactionType.INCOME.title) {
                val currentAccount = getDatabaseUseCases.getAccountUseCase(_account.value.title).first()
                val newIncomeAmount = currentAccount.income + amount
                val balance = newIncomeAmount - currentAccount.expense

                currentAccount.income = newIncomeAmount
                currentAccount.balance = balance

                getDatabaseUseCases.insertAccountsUseCase(listOf(currentAccount))
            } else {
                val currentAccount = getDatabaseUseCases.getAccountUseCase(_account.value.title).first()
                val newExpenseAmount = currentAccount.expense + amount
                val balance = currentAccount.income - newExpenseAmount

                currentAccount.expense = newExpenseAmount
                currentAccount.balance = balance

                getDatabaseUseCases.insertAccountsUseCase(listOf(currentAccount))
            }
            withContext(Main) {
                navigateBack()
            }
        }
    }

    fun setTransaction(amount: String) {
        val value = _transactionAmount.value
        val whole = value.substring(0, value.indexOf("."))

        if (amount == ".") {
            isDecimal.value = true
            return
        }

        if (isDecimal.value) {
            if (decimal.length == 2) {
                decimal = decimal.substring(0, decimal.length - 1) + amount
            } else {
                decimal += amount
            }
            val newDecimal = decimal.toDouble() / 100.0
            _transactionAmount.value = String.format("%.2f", whole.toDouble() + newDecimal)
            return
        }

        if (whole == "0") {
            _transactionAmount.value = String.format("%.2f", amount.toDouble())
        } else {
            _transactionAmount.value = String.format("%.2f", (whole + amount).toDouble())
        }
    }

    fun backspace() {
        val value = _transactionAmount.value
        var whole = value.substring(0, value.indexOf("."))

        if (value == "0.00") {
            return
        }

        if (isDecimal.value) {
            decimal = if (decimal.length == 2) {
                decimal.substring(0, decimal.length - 1)
            } else {
                isDecimal.value = false
                "0"
            }
            val newDecimal = decimal.toDouble() / 100.0
            _transactionAmount.value = String.format("%.2f", whole.toDouble() + newDecimal)
            decimal = String()
            return
        }

        whole = if (whole.length - 1 == 0)
            "0"
        else
            whole.substring(0, whole.length - 1)

        _transactionAmount.value = String.format("%.2f", whole.toDouble())
    }

    fun displayTransaction(
        transactionDate: String?,
        transactionIndex: Int?,
        transactionSort: Int?
    ) {
        if (transactionIndex != -1 && transactionSort != -1) {
            val transaction = if (transactionSort == 0)
                _dailyTransaction.value[transactionIndex!!]
            else {
                transactionDate?.let {
                    _monthlyTransaction.value[it]!![transactionIndex!!]
                }
            }

            setTransactionTitle(transaction!!.transactionTitle)

            _currentTime.value = transaction.date

            AccountsType.entries.forEach {
                if (it.title == transaction.account)
                    selectAccount(it)
                return@forEach
            }

            _transactionAmount.value = transaction.amount.toString()

            Categories.entries.forEach {
                if (it.title == transaction.category)
                    selectCategory(it)
            }
        }
    }

    fun updateTransaction(
        transactionDate: String?,
        transactionIndex: Int?,
        transactionSort: Int?,
        navigateBack: () -> Unit
    ) {
        if (transactionIndex != -1 && transactionSort != -1) {
            val transaction = if (transactionSort == 0)
                _dailyTransaction.value[transactionIndex!!]
            else {
                transactionDate?.let {
                    _monthlyTransaction.value[it]!![transactionIndex!!]
                }
            }

            viewModelScope.launch(IO) {
                if (_transactionAmount.value.toDouble() != transaction!!.amount) {
                    val currentAccount = getDatabaseUseCases.getAccountUseCase(_account.value.title).first()
                    if (transaction.transactionType == TransactionType.INCOME.title) {
                        currentAccount.income -= transaction.amount
                        currentAccount.income += _transactionAmount.value.toDouble()
                        currentAccount.balance = currentAccount.income - currentAccount.expense
                    } else {
                        currentAccount.expense -= transaction.amount
                        currentAccount.expense += _transactionAmount.value.toDouble()
                        currentAccount.balance = currentAccount.income - currentAccount.expense
                    }
                    getDatabaseUseCases.insertAccountsUseCase(listOf(currentAccount))
                }
                val updateTransaction = Transaction(
                    transaction.date,
                    transaction.dateOfEntry,
                    _transactionAmount.value.toDouble(),
                    _account.value.title,
                    _category.value.title,
                    transaction.transactionType,
                    _transactionTitle.value
                )
                getDatabaseUseCases.insertNewTransactionUseCase(updateTransaction)
                withContext(Main) {
                    navigateBack()
                }
            }
        }
    }

    fun displayExpenseLimitWarning() {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.getExpenseLimitUseCase().collectLatest { expenseLimitAmount ->
                if (expenseLimitAmount <= 0.0) return@collectLatest
                val threshold = 0.8 * expenseLimitAmount

                when {
                    _currentExpenseAmount.value > expenseLimitAmount -> {
                        val expenseOverflow =
                            (_currentExpenseAmount.value - expenseLimitAmount).toString().amountFormat()

                        val info =
                            "${_selectedCurrencyCode.value} $expenseOverflow over specified limit"
                        _limitAlert.emit(HomeUIEvent.Alert(info))
                    }

                    _currentExpenseAmount.value > threshold -> {
                        val expenseAvailable =
                            (expenseLimitAmount - _currentExpenseAmount.value).toString().amountFormat()
                        val info =
                            "${_selectedCurrencyCode.value} $expenseAvailable away from specified limit"
                        _limitAlert.emit(HomeUIEvent.Alert(info))
                    }
                    else -> _limitAlert.emit(HomeUIEvent.NoAlert())
                }
            }
        }
    }

    private fun currencyFormat() {
        viewModelScope.launch(IO) {
            getDatastoreUseCases.getCurrencyUseCase().collect { selectedCurrency ->
                _selectedCurrencyCode.value = selectedCurrency
            }
        }
    }
}
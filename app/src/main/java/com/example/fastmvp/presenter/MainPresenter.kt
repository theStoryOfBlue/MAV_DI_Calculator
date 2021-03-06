package com.example.fastmvp.presenter

import android.content.SharedPreferences
import android.util.Log
import com.example.fastmvp.Module.MainModule
import com.example.fastmvp.contract.MainContract
import com.example.fastmvp.contract.SharedpreferenceModule
import javax.inject.Inject

//추상화는
class MainPresenter @Inject constructor( //constructor가 무슨역할??
    private val module: SharedpreferenceModule // 이걸 통해서 쉐얼드프리퍼렌스모듈 사용하는게 맞나
) : MainContract.Presenter {

    var number1: String = ""
    var number2: String = ""
    var operator: String = ""

    init {
        println("   mainPresenter init   ")
        readLastResult()
    }

    override fun performCalculate() {
        val num1 = number1.toDoubleOrNull()
        val num2 = number2.toDoubleOrNull()

        if (number1.isEmpty() or  number2.isEmpty() or operator.isEmpty()) return

        if (num1 != null && num2 != null) {
            when (operator) {
                "+" -> {
                    number1 = (num1 + num2).toString()
                    number2 = ""
                    operator = ""
                }
                "-" -> {
                    number1 = (num1 - num2).toString()
                    number2 = ""
                    operator = ""
                }
                "x" -> {
                    number1 = (num1 * num2).toString()
                    number2 = ""
                    operator = ""
                }
                "/" -> {
                    number1 = (num1 / num2).toString()
                    number2 = ""
                    operator = ""
                }
                "%" -> {
                    number1 = (num1 % num2).toString()
                    number2 = ""
                    operator = ""
                }
            }
        }
        else return
    }

    override fun saveLastResult() {
        module.save(number1)
    }

    override fun readLastResult() {
        println("   Mainpresenter readLastresult   ")
        number1 = module.get()
        Log.e(javaClass.simpleName, "readLastResult: $number1", )
    }

    override fun updateView(): String {
        return number1 + operator + number2
    }

    override fun init() {
        number1 = ""
        number2 = ""
        operator = ""
    }

    // 추가로 작성하는 함수들은 여기다가 추가로 적기.
}
package com.knoldus.func

class Tax {
  def calculate: Int = 1000
}

class Salary {
  def tax: Box[Tax] = Full(new Tax)
}

class Employee {
  def salary: Box[Salary] = Full(new Salary)
}
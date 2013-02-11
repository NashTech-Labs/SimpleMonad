package com.knoldus.func

sealed trait Box[A] {
  def map[B](f: A => B): Box[B]
  def flatMap[B](f: A => Box[B]): Box[B]
}

case class Full[A](a: A) extends Box[A] {
  def map[B](f: A => B): Box[B] = new Full(f(a))
  def flatMap[B](f: A => Box[B]): Box[B] = f(a)
}

case class Empty[A] extends Box[A] {
  def map[B](f: A => B): Box[B] = new Empty
  def flatMap[B](f: A => Box[B]): Box[B] = new Empty
}

object Main2 extends App {
  def calculateTaxFromTax(tax: Tax): Int = tax.calculate

  def calculateTaxFromSalary(salary: Salary): Box[Int] = salary.tax.map { calculateTaxFromTax }

  def calculateTaxFromEmployee(employee: Employee): Box[Int] = employee.salary.flatMap(calculateTaxFromSalary)

  //def calculate(mayBeEmployee:Box[Employee]):Box[Int]=mayBeEmployee.flatMap{calculateTaxFromEmployee}

//  def calculate(mayBeEmployee: Box[Employee]): Box[Int] = {
//    mayBeEmployee.flatMap{ employee =>
//      employee.salary.flatMap{salary=>
//       salary.tax.map{tax=>
//         tax.calculate        
//       }  
//      }
//    }
//  }
  
  def calculate(mayBeEmployee: Box[Employee]): Box[Int] = {
    mayBeEmployee match{
      case Full(emp:Employee)=>emp.salary
                                match { case Full(salary:Salary)=>salary.tax
                                match { case Full(tax:Tax) => Full(tax.calculate)}
        }
    }
     
  }
  
}
package com.knoldus.func

case class Cage[+A](creature:A) {
   def map[B](f:A=>B):Cage[B]= Cage(f(creature))
   def flatMap[B](f:A=>Cage[B]):Cage[B]=f(creature)
}

case class Parrot(name: String) 
case class Patient(parrot:Parrot)
case class Rabbit(name:String)

object Main1 extends App{
  // Once there was a Parrot Frankey
  val patti=Parrot("Patti")
  val ricky = Rabbit("Ricky")
  
   
  //frankey don't have a cage to leave so give him a cage
  val cageOfPatti= Cage[Parrot](patti)
  
  // Beast is a parrotKiller
  
  //So far so good , but Ironically One Day patti becomes ill  
  // Now patti is patient so now it is cage of a Patient
  val cageOfPatient= cageOfPatti map parrotBecomesIll
  
  val cageOfRicky = cageOfPatient map patientReplacedByRabbit
  
  val cagePatty = cageOfRicky map rabbitReplacedByParrot
  
  val cagePatty2 = cageOfRicky flatMap rabbitReplacedByParrot
  
  def parrotBecomesIll(parrot:Parrot):Patient= Patient(patti)
  def patientReplacedByRabbit(patient:Patient):Rabbit = ricky
  def parriRecovers(patient:Patient):Parrot = patient.parrot
  def rabbitReplacedByParrot(rabbit:Rabbit):Cage[Parrot] = Cage(patti)
  
}
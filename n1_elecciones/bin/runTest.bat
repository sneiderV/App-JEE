@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n1_elecciones
REM Autor: Carlos Andr�s Vega - 29-ene-2007
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

java -classpath ../lib/elecciones.jar;../test/lib/eleccionesTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.elecciones.test.UrnaTest
java -classpath ../lib/elecciones.jar;../test/lib/eleccionesTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.elecciones.test.CandidatoTest
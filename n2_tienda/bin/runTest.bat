@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM $Id$
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2
REM Ejercicio: n2_tienda
REM Autor: Diana Puentes - 21-Jun-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecucion de la prueba
REM Archivo de ejecucion: tiendaTest.jar
REM Clase principal: uniandes.cupi2.tienda.test.ProductoTest
REM ---------------------------------------------------------

java -classpath ../lib/tienda.jar;../test/lib/tiendaTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.tienda.test.ProductoTest
java -classpath ../lib/tienda.jar;../test/lib/tiendaTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.tienda.test.TiendaTest
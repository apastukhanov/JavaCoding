@echo off

set CD=%~dp0

rem ����� 㪠����, ��� ����� JDK, �᫨ ��� �⮣� �� ��室���� ������� java
::set JAVA_HOME=C:\Program_Files\Java\jdk8

set JAVAW=javaw
if not "%JAVA_HOME%"=="" (
  set JAVAW="%JAVA_HOME%\bin\%JAVAW%"
)

%JAVAW% -jar "%CD%\SapperGame.jar" %*

:: Windows Batch Script (run-windows.bat)
@echo off
cd /d "%~dp0"

:: Set JavaFX SDK path for Windows
set JAVAFX_PATH=javafx-sdk-21\windows\javafx-sdk-21.0.6\lib

:: Compile Java files with JavaFX and include resources
mkdir target 2>nul
javac --module-path "%JAVAFX_PATH%" --add-modules javafx.controls,javafx.fxml -d target -cp src\main\java;src\main\resources src\main\java\project\*.java

:: Copy FXML and resources to target directory
xcopy /E /I /Y src\main\resources\project target\project

:: Run the application with JavaFX, ensuring resources are in classpath
java --module-path "%JAVAFX_PATH%" --add-modules javafx.controls,javafx.fxml -cp target project.FirePaaRadApp

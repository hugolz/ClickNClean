@echo off
cls

echo Deleting
cmd /c "C:\laragon\bin\mysql\mysql-8.0.30-winx64\bin\mysqladmin.exe -u root drop click_n_clean"

echo Creating new 
cmd /c "C:\laragon\bin\mysql\mysql-8.0.30-winx64\bin\mysqladmin.exe -u root create click_n_clean"

echo Loading sql file
cmd /c "C:\laragon\bin\mysql\mysql-8.0.30-winx64\bin\mysql.exe -u root click_n_clean < db/click_n_clean.sql"


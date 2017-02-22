@echo off
color 1b
set name=ExtractText
javac %name%.java -verbose -d %USERPROFILE%\Google Drive\Oswego++\Official Docs\Server\BinCode\classes
call setTimeAndDate.bat
echo Compiled %name%.java at %mm%-%dd%-%yy%_%hh%-%min%-%ss% >> "history\compileReport.log"
echo -
echo Report logged for %mm%-%dd%-%yy%_%hh%-%min%-%ss%!
copy %name%.java history\%name%%mm%-%dd%-%yy%_%hh%-%min%-%ss%.java
echo -
echo Copy created!
pause
exit
#!/bin/bash
if [ $# -lt 1 ]
then
	echo "Enter a commit message!"
	exit 1
fi
./Change.sh ld			#used for heroku
git add .
git commit -am "$1"
git push
./Change.sh dl			#Sets everything back to http://localhost:8082/etc...
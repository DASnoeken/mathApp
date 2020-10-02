#!/bin/bash
if [ $1 == 'dl' ]
then
	sed -i s/"https:\/\/daansmathapp.herokuapp.com"/"http:\/\/localhost:8082"/g ./src/main/resources/public/index.js
elif [ $1 == 'ld' ]
then
	sed -i s/"http:\/\/localhost:8082"/"https:\/\/daansmathapp.herokuapp.com"/g ./src/main/resources/public/index.js
else
	echo "use dl for daansmathapp to localhost or ld for localhost to daansmathapp!"
fi

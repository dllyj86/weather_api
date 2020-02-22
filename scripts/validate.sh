urlstatus=$(curl -s -m 5 -IL http://52.76.30.89:8888/api/weather/citylist|grep 200)
if [ "$urlstatus" == "200" ];then
	exit 0
else
	exit 1 
fi
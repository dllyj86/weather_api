urlstatus=$(curl -s -m 5 -IL http://52.76.30.89:8888/api/weather/citylist|grep 200)
if [ "$urlstatus" == "HTTP/1.1 200" ];then
	exit 0
else
	exit 1 
fi
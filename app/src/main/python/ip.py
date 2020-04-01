import requests
from bs4 import BeautifulSoup
import url
ip = "0.0.0.0"
headers={
    'User-Agent': 'Mozilla/5.0',
    'Cookie': 'Hm_lvt_22d5503e9164951ac850495fe15447a7=1585107930; PHPSESSID=ba87r1q3l064bqpvkp8d7f4hk6; Hm_lpvt_22d5503e9164951ac850495fe15447a7=1585138344'
}
def getHTMLText(ip):
    r = requests.get(url.url+ip,headers=headers)
    print(r.status_code)
    return r.text
def setIP(p):
    global ip
    ip=p
    str=""
    html = getHTMLText(ip)
    soup = BeautifulSoup(html,"html.parser")
    kv = {'class':'value'}
    s = soup.find_all(name='span',attrs=kv)
    for i in range(len(s)):
        if(i<len(s)-1):
            str+=s[i].string+":"
        else:
            str+=s[i].string
    return str
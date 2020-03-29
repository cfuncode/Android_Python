import requests
from bs4 import BeautifulSoup
url = "https://ipchaxun.com/"
ip = "80.82.70.187"
headers={
    'User-Agent': 'Mozilla/5.0',
    'Cookie': 'Hm_lvt_22d5503e9164951ac850495fe15447a7=1585107930; PHPSESSID=ba87r1q3l064bqpvkp8d7f4hk6; Hm_lpvt_22d5503e9164951ac850495fe15447a7=1585138344'
}
r = requests.get(url+ip,headers=headers)
print(r.status_code)
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
kv = {'class':'value'}
str = ["IP地址","归属地","运营商"]
s = soup.find_all(name='span',attrs=kv)
for i in range(len(s)):
    print(str[i] + "：" + s[i].string)

# label = soup.body.label
# label2 = label.next_sibling.next_sibling
# label3 = label2.next_sibling.next_sibling
# print("IP地址："+label.span.next_sibling.string)
# print("归属地："+label2.span.next_sibling.string)
# print("运营商："+label3.span.next_sibling.string)
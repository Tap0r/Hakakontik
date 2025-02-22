import requests
from bs4 import BeautifulSoup as bs
import json

def get_full_description(url):
    req = requests.get(url)
    html = req.text
    soup = bs(html, 'lxml')
    text = soup.find(class_='news-content').text
    return text

def add_news_from_page():
    news = soup.find_all('div', class_='card-body')
    for new in news:
        row = {}
        row['title'] = new.find(class_='card-title').text
        row['link'] = new.find('a').get('href')
        row['short_description'] = new.find(class_='mb-2').text
        row['full_description'] = get_full_description(row['link'])
        row['date'] = new.find(class_='text-muted').text
        res.append(row)
res = []

start_page = 1
url = f'https://ssau.ru/news'

req = requests.get(url)
html = req.text
soup = bs(html, 'lxml')

max_page = int(soup.find_all(class_='page-link')[-2].text)

for page in range(start_page, 3+1):
    url = f'https://ssau.ru/news?page={page}'
    add_news_from_page()

print(len(res))
print(res)

with open('data.json', 'w', encoding='utf-8') as file:
    json.dump(res, file, ensure_ascii=False, indent=4)
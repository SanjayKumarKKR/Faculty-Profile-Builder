import scholar
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
from firebase_admin import firestore
import re
cred = credentials.Certificate("/Users/yogeshm/Documents/SE-Project/Datascrape/se-project-bd8e9-firebase-adminsdk-gixw8-0ed577af82.json")
firebase_admin.initialize_app(cred)
db = firestore.client()

def cleanhtml(raw_html):
  cleanr = re.compile('<.*?>')
  cleantext = re.sub(cleanr, '', raw_html)
  return cleantext

lis=[]

for j in lis:
    print("Starting... "+j)
    search_query = scholar.search_author(j+' @cb.amrita.edu')
    for i in search_query:
        i=i.fill()
        print(i)
        l=[]
        for pub in i.publications:
            try:
                k = pub.fill()
                c = {
                    'abstracts': cleanhtml(str(k.bib['abstract'])),
                    'title': k.bib['title'],
                    'url': k.bib['url'],
                    'year': k.bib['year'],
                    'author': k.bib['author'],
                    'citedby': k.citedby,
                    'publisher': k.bib['publisher']
                }
                print(c)
                l.append(c)
            except Exception:
                continue

        con = {
            'name': i.name,
            'publications': l,
            'citedby': i.citedby,
            'hindex': i.hindex,
            'hindex5y': i.hindex5y,
            'i10index': i.i10index,
            'i10index5y':i.hindex5y,
            'interests':i.interests,
            'url_picture': i.url_picture
            }
        print(con)
        doc_ref = db.collection('professors').document(i.name)
        doc_ref.set(con)
        print("Endind... "+j)

#!/usr/bin/env python
# coding: utf-8

# In[7]:


import pickle


# In[17]:


list_pickle_path = '../Dataset/model.pkl'
list_unpickle = open(list_pickle_path, 'rb')


# In[18]:


model=pickle.load(list_unpickle)


# 

# In[19]:


import pandas as pd
import numpy as np


# In[20]:


test=pd.read_csv('../Dataset/test.csv')



# In[15]:


X_test=test.iloc[:,4:18]
Y_test=test['isHacker']


# In[16]:


Y_predict=model.predict(X_test)


# In[ ]:


Y_Predict=Y_predict.tolist()
# In[22]:


unique,count= np.unique(Y_Predict,return_counts=True)
total=count[0]+count[1]
hack_chance=count[1]*100/total
flag=0
if(hack_chance>=30):
    flag=1


# In[21]:


Output={
    "pname":test.iloc[0,0],
    "team":int(test.iloc[0,1]),
    "kills":int(test.iloc[-1,2]),
    "deaths":int(test.iloc[-1,3]),
    "isHacker": flag
    }
print(Output)

# In[ ]:


import json
import codecs


# In[ ]:



jsonObject= json.dumps(Output)
filepath = "../Model/Output.json"
with open(filepath,"w") as outfile:
    outfile.write(jsonObject)

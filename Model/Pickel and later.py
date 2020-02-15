#!/usr/bin/env python
# coding: utf-8

# In[7]:


import pickle


# In[17]:


list_pickle_path = 'D:\Study\Projects\Project\EagleEye\Dataset\model.pkl'
list_unpickle = open(list_pickle_path, 'rb')


# In[18]:


model=pickle.load(list_unpickle)


# 

# In[19]:


import pandas as pd


# In[20]:


test=pd.read_csv('D:\Study\Projects\Project\EagleEye\Dataset\test.csv')


# In[13]:


test


# In[15]:


X_test=test.iloc[:,4:18]
Y_test=test['isHacker']


# In[16]:


model.predict(X_test)


# In[ ]:


matrix=confusion_matrix(Y_predict,Y_test)
matrix


# In[ ]:


Y_Predict=Y_predict.tolist()
Y_Predict


# In[22]:


unique,count= np.unique(Y_Predict,return_counts=True)
total=count[0]+count[1]
hack_chance=count[1]*100/total
flag=0
if(hack_chance>=30):
    flag=1


# In[21]:


Output=[test.iloc[0,0],test.iloc[0,1],test.iloc[-1,2],test.iloc[-1,3],flag]


# In[ ]:


import json
import codecs


# In[ ]:


filepath = "D:\Study\Projects\Project\EagleEye\Model\Output.json"
json.dump(Output,codecs.open(filepath,"w",encoding="utf-8"), separators=(',',':'), sort_keys=True, indent=4)


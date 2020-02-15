#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
import numpy as np


# In[2]:


dataset=pd.read_csv('..\Dataset\Dataset.csv')



# In[4]:


X=dataset.iloc[:,4:18]
Y=dataset['isHacker']


# In[5]:


from sklearn.model_selection import GridSearchCV
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import confusion_matrix


# In[6]:


dtc=DecisionTreeClassifier(random_state=0)
parameters={'max_depth':range(3,20)}
model=GridSearchCV(estimator=dtc,param_grid=parameters,cv=10,n_jobs=4)


# In[7]:


model.fit(X,Y)


# In[8]:


import pickle


# In[9]:


dtc=DecisionTreeClassifier(random_state=0,max_depth=3)
X_train,X_test,Y_train,Y_test=train_test_split(X,Y,test_size=0.3,random_state=0)
model.fit(X_train,Y_train)
Y_predict=model.predict(X_test)


# In[10]:


temp = open("..\Dataset\model.pkl", "wb")
pickle.dump(model, temp)
temp.close()







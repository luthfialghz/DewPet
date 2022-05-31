import tensorflow as tf
import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder

# baca file
dataframe = pd.read_csv('pet_dataset.csv')

# hapus "Animal Type" column
#traintest_data = dataframe.drop(["Animal Type"], axis=1)
#traintest_data = dataframe.copy()

label_encoder = LabelEncoder()
traintest_data.loc[:,"Animal Type"] = label_encoder.fit_transform(traintest_data.iloc[:,-2])

# split x and y 
x_train = train.drop(['Diagnosis'], axis=1)
y_train = np.array(train['Diagnosis'])

x_test = test.drop(['Diagnosis'], axis=1)
y_test = np.array(test['Diagnosis'])

y_train_encode = pd.get_dummies(y_train)
y_test_encode = pd.get_dummies(y_test)

# buat model
model = tf.keras.models.Sequential([
    #tf.keras.layers.Flatten(),
    #tf.keras.layers.Dense(64, activation='relu', input_dim=x_train.shape[1]),
    tf.keras.layers.Dense(128, activation='relu', input_shape= (x_train.shape[1], )),
    tf.keras.layers.Dense(64, activation='relu'),
    tf.keras.layers.Dense(64, activation='relu'),
    #tf.keras.layers.Dense(256, activation='relu'),
    tf.keras.layers.Dense(y_train_encode.shape[1], activation='softmax')
])

# compile model
model.compile(loss=tf.keras.losses.CategoricalCrossentropy(), optimizer=tf.optimizers.Adam(), metrics=['accuracy'])

# train model
history = model.fit(x_train, y_train_encode, epochs=50, validation_split=0.3)

#model.evaluate()

#print(history.history["accuracy"])

import pickle
filename = 'model.pkl'
pickle.dump(model, open(filename, 'wb'))

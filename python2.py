from random import shuffle
import tensorflow as tf
import pandas as pd
import numpy as np
import csv

# baca file
dataframe = pd.read_csv('D:\BANGKIT 2022\Capstone Project\Code\pet_dataset.csv')

# hapus "Animal Type" column
traintest_data = dataframe.drop(["Animal Type"], axis=1)

data_shuffled = traintest_data.sample(frac=1, random_state=1)
# split x and y 
x_train = np.array(data_shuffled.iloc[:, :-1])
y_train = np.array(data_shuffled.iloc[:, -1])

#x_test = np.array(data_shuffled.iloc[-7:, :-1])
#y_test = np.array(data_shuffled.iloc[-7:, -1])

y_train_encode = pd.get_dummies(y_train)
#y_test_encode = pd.get_dummies(y_test)

# buat model
model = tf.keras.models.Sequential([
    tf.keras.layers.Dense(128, activation='relu', input_shape=(x_train.shape[1],)),
    tf.keras.layers.Dense(64, activation='relu'),
    tf.keras.layers.Dense(64, activation='relu'),
    tf.keras.layers.Dense(y_train_encode.shape[1], activation='softmax')
])

# compile model
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

# train model
history = model.fit(x_train, y_train_encode, epochs=100, validation_split=0.1)

#model.evaluate()

#print(history.history["accuracy"])
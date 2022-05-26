import tensorflow as tf
import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder

# baca file
dataframe = pd.read_csv('pet_dataset.csv')

# hapus "Animal Type" column
#traintest_data = dataframe.drop(["Animal Type"], axis=1)

traintest_data = dataframe.copy()

label_encoder = LabelEncoder()
traintest_data.loc[:,"Animal Type"] = label_encoder.fit_transform(traintest_data.iloc[:,-2])

# split x and y 
x_train = traintest_data.drop(['Diagnosis'], axis=1)
y_train = traintest_data['Diagnosis']

#x_test = np.array(data_shuffled.iloc[-7:, :-1])
#y_test = np.array(data_shuffled.iloc[-7:, -1])

y_train_encode = pd.get_dummies(y_train)
#y_test_encode = pd.get_dummies(y_test)

# buat model
model = tf.keras.models.Sequential([
    tf.keras.layers.Dense(64, activation='relu', input_dim=x_train.shape[1]),
    tf.keras.layers.Dense(32, activation='relu'),
    #tf.keras.layers.Dense(32, activation='relu'),
    #tf.keras.layers.Dense(16, activation='relu'),
    tf.keras.layers.Dense(y_train_encode.shape[1], activation='softmax')
])

# compile model
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

# train model
history = model.fit(x_train, y_train_encode, epochs=100, validation_split=0.2, shuffle=True)

#model.evaluate()

#print(history.history["accuracy"])

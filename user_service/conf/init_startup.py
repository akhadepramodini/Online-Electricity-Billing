import os

from chatterbot import ChatBot
from chatterbot.trainers import ListTrainer
from flask import Flask


def init_startup():
    app = Flask(__name__)
    dir_path = os.path.join(
        os.path.dirname(os.path.dirname(os.path.realpath(__file__))) + '/Files')
    print dir_path

    app.bot = ChatBot('Test')
    for file in os.listdir(dir_path):
        print  'File/' + file
        chats = open(dir_path + '/' + file, 'r').readlines()

        app.bot.set_trainer(ListTrainer)
        #app.bot.train(chats)
    return app

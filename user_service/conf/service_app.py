import django



django.setup()




from flask_restful import Api
from user_service.conf.init_startup import init_startup
from user_service.service_apis.BIllPDF import ViewPDF
from user_service.service_apis.bot import Bot
from user_service.service_apis.notification import Notification_history
from user_service.service_apis.Validate import Validate
from user_service.service_apis.ping import Ping
from user_service.service_apis.user import UserHandler
from user_service.service_apis.login import Login
from user_service.service_apis.logout import Logout
#from user_service.service_apis.question import Question
from user_service.service_apis.bill import Bill_Pdf_Display
from user_service.service_apis.connection import Connection
from user_service.service_apis.complaint import Complaint
# from user_service.service_apis.chatbot import ChatBot
from user_service.service_apis.history import Bill_History
from user_service.service_apis.pay import Pay
from user_service.service_apis.track import Track
from user_service.service_apis.admin import Admin
from user_service.service_apis.amount import Amount

app = init_startup()
api = Api(app)

api.add_resource(Ping, '/ping')
api.add_resource(Bot, '/bot')
api.add_resource(UserHandler, '/user', '/user/<string:username>')
api.add_resource(Login, '/login')
api.add_resource(Logout, "/logout")
api.add_resource(Validate, '/validate')
api.add_resource(Connection, '/connection')
#api.add_resource(Question, '/question')
api.add_resource(Bill_Pdf_Display, "/bill")
api.add_resource(ViewPDF, "/view/pdf/<filename>")
api.add_resource(Complaint, "/complaint")
api.add_resource(Bill_History, "/bill_history")
api.add_resource(Notification_history,'/notification')
api.add_resource(Amount,'/amount')
api.add_resource(Pay,'/pay')
api.add_resource(Track,'/track')
api.add_resource(Admin,'/train')

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8090, debug=True)

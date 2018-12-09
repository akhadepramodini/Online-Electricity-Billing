from flask import request
from flask_restful import Resource
from user_service.db.user_models.models import BillingEntry, Connection, User


class Pay(Resource):
    def post(self):
        request_data = request.get_json()
        bill_id = request_data['bill_id']
        bill_object = BillingEntry.objects.filter(id=bill_id).last()
        try:
                bill_object.is_paid=True
                bill_object.save()
                return {"result":"Payment successful"}
        except Exception as e:
            print e
            return None

    def put(self):
        request_data = request.get_json()
        customer_id = request_data['customer_id']
        connection_object = Connection.objects.get(customer_id=customer_id)
        user_object = User.objects.get(connection=connection_object)
        bill_object = BillingEntry.objects.filter(user=user_object).last()
        if bill_object:
            bill_object.is_paid = True
            bill_object.save()
            return {"result": "Payment successful"}
        else:
            return {"result": "Payment Unsuccessful"}

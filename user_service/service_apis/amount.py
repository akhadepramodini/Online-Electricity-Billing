from flask import request,jsonify
from flask_restful import Resource

from user_service.db.user_models.models import Connection, BillingEntry, User
from user_service.utils.amount_dict import get_amount_object


class Amount(Resource):
    def post(self):
        request_data = request.get_json()
        customer_id = request_data['customer_id']
        connection_object = Connection.objects.get(customer_id=customer_id)
        user_object = User.objects.get(connection=connection_object)
        bill_object = BillingEntry.objects.filter(user=user_object).last()
        amount_object = get_amount_object(bill_object)
        if amount_object:
            return jsonify({"Amount": amount_object})
        else:
            return {"result": "Payment Unsuccessful"}
1
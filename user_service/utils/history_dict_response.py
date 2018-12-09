from user_service.utils.user_related_methods import get_user_dict_response


def history_dict_response(history_object):


    response_dict = {
                     'name': history_object.user.connection.name,
                     'billing_units': history_object.billing_units,
                     'bill_amount' :history_object.bill_amount,
                     "last_date":history_object.last_date
                     }

    return response_dict
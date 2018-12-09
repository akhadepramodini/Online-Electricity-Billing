from user_service.utils.user_related_methods import get_user_dict_response


def get_dict_for_bill_object(bill_object):
    response_dict = {"user": get_user_dict_response(bill_object.user),
                     "month": bill_object.month.strftime(
                         "%d/%m/%Y"),
                     'billing_units': bill_object.billing_units,
                     'bill_amount' :bill_object.bill_amount}
    return response_dict
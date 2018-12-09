
def get_amount_object(bill_object):
    print bill_object.bill_amount
    response_dict = {"bill_amount": bill_object.bill_amount
                     }
    return response_dict

from datetime import datetime

from user_service.constants.rate_per_unit import RATE_PER_UNIT
from user_service.db.user_models.models import BillingEntry
from user_service.utils.exceptions import GenericCustomException
from user_service.utils.user_related_methods import \
    check_credentials_and_get_object


def bill_entry(request_data):
    username = request_data['username']
    customer_id = request_data['customerId']
    month_date = datetime.fromtimestamp(request_data['date'])
    units = request_data['units']

    user_object = check_credentials_and_get_object(username, customer_id)
    pending_bills = get_pending_bills_for_user(user_object)

    total_amount = 0
    for o in pending_bills:
        total_amount += o.bill_amount

    total_amount += (units * RATE_PER_UNIT)

    try:
        bill_entry = BillingEntry.objects.get(user=user_object,
                                              month=month_date,
                                              bill_amount=total_amount,
                                              billing_units=units)
        return bill_entry
    except:
        raise GenericCustomException(message="Error while creating bill !!")


def get_pending_bills_for_user(user_object):
    return BillingEntry.objects.filter(user=user_object, is_paid=False)

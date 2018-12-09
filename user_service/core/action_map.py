from user_service.core.action_methods import get_my_bill, complaint_status, \
    bill_History, bill_History1, complaint_registration_for_bot, \
    screenshot_app_register, screenshot_bill_complaint, complaint_registration, \
    unable_to_response


def get_action_map(request_string):
    action_map ={

       "Generate bill pdf": get_my_bill,
        "complaint_status": complaint_status,
        "bill_History":bill_History,
        "BillLastdate":bill_History1,
        "newComplaint_registration": complaint_registration_for_bot,
        "Screenshot_newConnection": screenshot_app_register,
        "Screenshot_bill":screenshot_bill_complaint,
        "complaint_registration":complaint_registration,
        "unable_to_response":unable_to_response

    }
    return action_map[request_string]

 #     "Screenshot_newConnection":,
    #"complaint_registration":

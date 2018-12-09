import way2sms
def send_sms(receiver, message):
    '''
    reciver = str
    message = str
    :param receiver:
    :param message:
    :return:
    '''
    q = way2sms.sms(receiver, message)
    q.send(receiver.decode('utf-8'), message)
    q.logout()
    return True
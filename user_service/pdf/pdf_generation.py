from reportlab.graphics.barcode import eanbc, qr, usps
from reportlab.graphics.shapes import Drawing
from reportlab.lib.pagesizes import letter
from reportlab.lib.colors import HexColor
from reportlab.pdfgen import canvas
from reportlab.graphics import renderPDF
import os
import django; django.setup()
from user_service.db.user_models.models import BillingEntry
dir_path = os.path.dirname(os.path.realpath(__file__))

def send_pdf(bill_object):


    print bill_object.user
    username = bill_object.user.username
    month = bill_object.month
    bill_unit = bill_object.billing_units
    bill_amount = bill_object.bill_amount
    billname = str(username) + str(month) + ".pdf"
    code = str(bill_object.id) + ":" + str(bill_object.bill_amount)
    address = str(bill_object.user.connection.survey_number)+" "+ str(bill_object.user.connection.society_name) +" "+  str(bill_object.user.connection.village)
    address1 = str(bill_object.user.connection.taluka) +" "+  str(bill_object.user.connection.district) +" "+  str(bill_object.user.connection.pincode)
    path = os.path.join(dir_path + "/static", billname)


    c= canvas.Canvas(path, pagesize=letter)
    c.drawImage("/home/shree/Shubham/final project of hacathon/hackathon_user_service-master/user_service/pdf/powerheader.jpg",2,670,600,95)
    c.setFillColor(HexColor('#ffffff'))
    c.setFontSize(size=12)
    c.setFillColor(HexColor('#000000'))
    c.drawString(15,630,"--------------------------------------------------------------------------------------------------------------------------------------------------")
    c.drawString(15,200,"--------------------------------------------------------------------------------------------------------------------------------------------------")
    c.drawString(20, 600, "Customer_ID :")
    c.drawString(250,600,str(bill_object.user.connection.customer_id))
    c.drawString(20, 550, "Name:")
    c.drawString(250,550,bill_object.user.connection.name)
    c.drawString(20,350,"Address:")
    c.drawString(250,350,str(address))
    c.drawString(250,380,str(address1))
    c.drawString(20, 500, "month:")
    c.drawString(250, 500, month)
    print type(code)

    c.drawString(20, 450, "Mobile No:")
    c.drawString(250,450,str(bill_object.user.phone))
    c.drawString(20,400,"Billing_units:")
    c.drawString(250,400,str(bill_unit))
    c.drawString(20, 300, "Billing Amount:")
    c.drawString(250, 300, str(bill_amount))
    c.drawString(20, 250, "Last Date:")
    c.drawString(250, 250, str(bill_object.last_date))
    qr_code = qr.QrCodeWidget(code)
    bounds = qr_code.getBounds()
    width = bounds[2] - bounds[0]
    height = bounds[3] - bounds[1]
    d = Drawing(110, 110, transform=[110. / width, 0, 0, 110. / height, 0, 0])
    d.add(qr_code)
    renderPDF.draw(d, c, 490, 520)
    c.drawImage("/home/shree/Shubham/final project of hacathon/hackathon_user_service-master/user_service/pdf/3.jpg",2,-5,620,200)
    c.save()
    return billname

if __name__ == "__main__":
    bill_object = BillingEntry.objects.first()
    send_pdf(bill_object)


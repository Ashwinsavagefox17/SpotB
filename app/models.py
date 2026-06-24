from django.db import models

# Create your models here.
class Login(models.Model):
    username = models.CharField(max_length=200)
    password = models.CharField(max_length=200)
    usertype = models.CharField(max_length=200)

class Passenger(models.Model):
    pname = models.CharField(max_length=200)
    LOGIN = models.ForeignKey(Login,on_delete = models.CASCADE)
    place = models.CharField(max_length=200)
    post = models.CharField(max_length=200)
    pincode = models.CharField(max_length=200)
    city = models.CharField(max_length=200)
    district = models.CharField(max_length=200)
    photo = models.FileField()
    phone=models.CharField(max_length=100,default="")


class Driver(models.Model):
    LOGIN = models.ForeignKey(Login,on_delete = models.CASCADE)
    driver_name = models.CharField(max_length=200)
    contact_number = models.CharField(max_length=200)
    place = models.CharField(max_length=200)
    post = models.CharField(max_length=200)
    pincode = models.CharField(max_length=200)
    city = models.CharField(max_length=200)
    district = models.CharField(max_length=200)
    photo = models.FileField()
    licenses = models.FileField()


class Owner(models.Model):
    LOGIN = models.ForeignKey(Login,on_delete = models.CASCADE)
    ower_name = models.CharField(max_length=200)
    contact_number = models.CharField(max_length=200)
    place = models.CharField(max_length=200)
    post = models.CharField(max_length=200)
    pincode = models.CharField(max_length=200)
    city = models.CharField(max_length=200)
    district = models.CharField(max_length=200)
    photo = models.FileField()
    id_proof = models.FileField()
    status = models.CharField(max_length = 255)


class Route(models.Model):
    routes_start = models.CharField(max_length=200)
    routes_End = models.CharField(max_length=200)


class Stops(models.Model):
    ROUTE = models.ForeignKey(Route,on_delete = models.CASCADE)
    point_name = models.CharField(max_length=200)
    stop_no = models.CharField(max_length=200)
    charge = models.CharField(max_length=200,default='')
    lattitude = models.CharField(max_length=200,default='')
    longitude = models.CharField(max_length=200,default='')


class Bus(models.Model):
    OWER = models.ForeignKey(Owner,on_delete =models.CASCADE)
    bus_number = models.CharField(max_length=200)
    bus_name = models.CharField(max_length=200)
    status = models.CharField(max_length=200)



class Complaints(models.Model):
    PASSENGER = models.ForeignKey(Passenger,on_delete =models.CASCADE)
    BUS = models.ForeignKey(Bus,on_delete =models.CASCADE,default=1)
    complaint =models.CharField(max_length=200)
    reply = models.CharField(max_length=200)
    date = models.CharField(max_length=200)
 

class Allocation(models.Model):
    DRIVER = models.ForeignKey(Driver,on_delete =models.CASCADE,default="")
    BUS = models.ForeignKey(Bus,on_delete =models.CASCADE)
    ROUTE = models.ForeignKey(Route,on_delete =models.CASCADE)
    OWER = models.ForeignKey(Owner,on_delete =models.CASCADE)

    

class Checkin(models.Model):
    PASSENGER = models.ForeignKey(Passenger,on_delete =models.CASCADE,default="")
    BUS = models.ForeignKey(Bus,on_delete =models.CASCADE)
    date=models.DateField()
    time=models.TimeField()
    Checkin_stop=models.ForeignKey(Stops,on_delete =models.CASCADE,related_name="fromstop")
    Checkout_stop=models.ForeignKey(Stops,on_delete =models.CASCADE,related_name="tostop")


class Feedback(models.Model):
    PASSENGER = models.ForeignKey(Passenger,on_delete =models.CASCADE)
    complaint =models.CharField(max_length=200)
    reply = models.CharField(max_length=200)
    date = models.CharField(max_length=200)

class Rating(models.Model):
    PASSENGER = models.ForeignKey(Passenger,on_delete =models.CASCADE)
    rating =models.CharField(max_length=200)
    date = models.CharField(max_length=200)


class Trip(models.Model):
    ALLOCATION = models.ForeignKey(Allocation,on_delete =models.CASCADE)
    start_time = models.CharField(max_length=200)
    End_time = models.CharField(max_length=200)
   

class Track(models.Model):
    DRIVER = models.ForeignKey(Driver,on_delete =models.CASCADE)
    time = models.CharField(max_length=200)
    date = models.CharField(max_length=200)
    lattitude = models.CharField(max_length=200)
    longitude = models.CharField(max_length=200)
    place = models.CharField(max_length=200)

class Bus_status(models.Model):
    ALLOCATION = models.ForeignKey(Allocation,on_delete =models.CASCADE)
    status = models.CharField(max_length=200)
    date = models.CharField(max_length=200)




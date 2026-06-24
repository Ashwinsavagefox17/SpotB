from django.http import JsonResponse
from django.shortcuts import render
from django.shortcuts import render,redirect,HttpResponse
from django.core.files.storage import FileSystemStorage
from itsdangerous import Serializer
from app.models import *


def home(request):
    return render(request,'index1.html')   


def login(request):
    if 'submit' in request.POST:
        username = request.POST['username']
        password = request.POST['password']
        if Login.objects.filter(username=username,password=password).exists():
            qa = Login.objects.get(username=username,password=password)
            request.session['lid']=qa.pk
            if qa.usertype =='admin':
                return HttpResponse(f"<script>alert('welcome Admin');window.location='admin_home'</script>")
            if qa.usertype =='owner':
                q1 = Owner.objects.get(LOGIN_id=request.session['lid'],status='Approved')
                if q1:
                    request.session['oid']=q1.pk
                    return HttpResponse(f"<script>alert('welcome Owner');window.location='owner_home'</script>")
            else:
                return HttpResponse(f"<script>alert('No User Found');window.location='login'</script>")
        else:
            return HttpResponse(f"<script>alert('invalid username or password');window.location='login'</script>")
    return render(request,'login.html')


def ownerreg(request):
    if 'submit' in request.POST:
        ower_name = request.POST['ower_name']
        contact_number = request.POST['contact_number']
        place = request.POST['place']
        post = request.POST['post']
        pincode = request.POST['pincode']
        city = request.POST['city']
        district = request.POST['district']

        photos=request.FILES['photo']
        fs=FileSystemStorage()
        vv=fs.save(photos.name,photos)

          
        id_proof=request.FILES['id_proof']
        fs=FileSystemStorage()
        vv=fs.save(id_proof.name,id_proof)

        username = request.POST['username']
        password = request.POST['password']

        q = Login(username=username,password=password,usertype='owner')
        q.save()
        q1 = Owner(ower_name=ower_name,contact_number=contact_number,place=place,post=post,pincode=pincode,district=district,city=city,photo=photos,id_proof=id_proof,LOGIN_id=q.pk,status='pending')
        q1.save()
        print(q1)
        return HttpResponse(f"<script>alert('Owner Succesfully Registerd');window.location='/'</script>")
    # return render(request,'admin/admin_manage_driver.html',{'q':q})
    return render(request,'ownerreg.html')







def admin_home(request):
    return render(request,'admin/admin_home.html')


def admin_manage_driver(request):
    q=Driver.objects.all()
    if 'submit' in request.POST:
        driver_name = request.POST['driver_name']
        contact_number = request.POST['contact_number']
        place = request.POST['place']
        post = request.POST['post']
        pincode = request.POST['pincode']
        city = request.POST['city']
        district = request.POST['district']

        photos=request.FILES['photo']
        fs=FileSystemStorage()
        vv=fs.save(photos.name,photos)

          
        licenses=request.FILES['licenses']
        fs=FileSystemStorage()
        vv=fs.save(licenses.name,licenses)

        username = request.POST['username']
        password = request.POST['password']

        q = Login(username=username,password=password,usertype='driver')
        q.save()
        q1 = Driver(driver_name=driver_name,contact_number=contact_number,place=place,post=post,pincode=pincode,district=district,city=city,photo=photos,licenses=licenses,LOGIN_id=q.pk)
        q1.save()
        print(q1)
        return HttpResponse(f"<script>alert('Registerd');window.location='/admin_manage_driver'</script>")
    return render(request,'admin/admin_manage_driver.html',{'q':q})


from django.shortcuts import render, get_object_or_404

def driver_update(request, id):
    q1 = get_object_or_404(Driver, id=id)
    
    if request.method == 'POST':
        driver_name = request.POST.get('driver_name')
        contact_number = request.POST.get('contact_number')
        place = request.POST.get('place')
        post = request.POST.get('post')
        pincode = request.POST.get('pincode')
        city = request.POST.get('city')
        district = request.POST.get('district')
        
        q1.driver_name = driver_name
        q1.contact_number = contact_number
        q1.place = place
        q1.post = post
        q1.pincode = pincode
        q1.city = city
        q1.district = district
        
        if 'photo' in request.FILES:
            photo = request.FILES['photo']
            fs = FileSystemStorage()
            photo_name = fs.save(photo.name, photo)
            q1.photo = photo_name
        
        if 'licenses' in request.FILES:
            licenses = request.FILES['licenses']
            fs = FileSystemStorage()
            licenses_name = fs.save(licenses.name, licenses)
            q1.licenses = licenses_name
        
        q1.save()
        return HttpResponse("<script>alert('Updated Driver Details');window.location='/admin_manage_driver'</script>")
    
    return render(request, 'admin/admin_manage_driver.html', {'q1': q1})



def driver_delete(request,id):
    q2 = Driver.objects.get(id=id)
    q2.delete()
    return HttpResponse(f"<script>alert('deleted Driver Details ');window.location='/admin_manage_driver'</script>")



def view_owner(request):
    q=Owner.objects.all()
    
    return render(request,'admin/view_owner.html',{'q':q})


def owner_approve(request,ids):
    q=Owner.objects.get(LOGIN_id=ids)
    print(q,'result')
    w=Login.objects.get(id=q.LOGIN_id)
    print(w,'////////////////////////////////////////////')
    q.status='Approved'
    w.usertype = 'owner'
    q.save()
    w.save()
    return HttpResponse(f"<script>alert('Approved');window.location='/view_owner'</script>")



def owner_reject(request,ids):
    q=Owner.objects.get(LOGIN_id=ids)
    print(q,'result')
    w=Login.objects.get(id=q.LOGIN_id)
    print(w,'////////////////////////////////////////////')
    q.status='rejected'
    w.usertype = 'pending'
    q.save()
    w.save()
    return HttpResponse(f"<script>alert('Rejected');window.location='/view_owner'</script>")



def owner_delete(request,id):
    q2 = Owner.objects.get(id=id)
    w = Login.objects.get(id=q2.LOGIN_id)
    q2.delete()
    w.delete()
    return HttpResponse(f"<script>alert(' Owner Details Deleted ');window.location='/view_owner'</script>")

def admin_mange_routes(request):
    q1 = Route.objects.all()
    if 'submit' in request.POST:
        routes_start = request.POST['routes_start']
        routes_End = request.POST['routes_End']
        if Route.objects.filter(routes_start=routes_start,routes_End=routes_End).exists():
            return HttpResponse(f"<script>alert('Route already exists');window.location='/admin_mange_routes'</script>")
        q = Route(routes_start=routes_start,routes_End=routes_End)
        q.save()
        return HttpResponse(f"<script>alert(' Route Added ');window.location='/admin_mange_routes'</script>")
    return render(request,'admin/admin_mange_routes.html',{'q1':q1})


def update_routes(request,id):
    q1 = Route.objects.get(id=id)
    if request.method == 'POST' and 'submit' in request.POST:
        routes_start = request.POST.get('routes_start')
        routes_End = request.POST.get('routes_End')

        # Check if the new values are different from the existing ones
        if routes_start != q1.routes_start or routes_End != q1.routes_End:
            # Check if the new values already exist in another route
            if Route.objects.filter(routes_start=routes_start, routes_End=routes_End).exists():
                return HttpResponse("<script>alert('Route already exists');window.location='/admin_mange_routes'</script>")
            
            # Update the attributes of q1 with the new values
            q1.routes_start = routes_start  
            q1.routes_End = routes_End
            q1.save()

            return HttpResponse("<script>alert('Route updated');window.location='/admin_mange_routes'</script>")
        else:
            return HttpResponse("<script>alert('No changes to update');window.location='/admin_mange_routes'</script>")
    
    return render(request, 'admin/admin_mange_routes.html', {'q2': q1})



def delete_routes(request,id):
    q = Route.objects.get(id=id)
    q.delete()
    return HttpResponse(f"<script>alert(' Route Deleted ');window.location='/admin_mange_routes'</script>")
    
def admin_manage_stops(request,id):
    q1 = Stops.objects.filter(ROUTE_id=id)
    route = Route.objects.get(id=id)
    if 'submit' in request.POST:
        point_name = request.POST['point_name']
        stop_no = request.POST['stop_no']
        charges = request.POST['charges']
        latitude = request.POST['latitude']
        longitude = request.POST['longitude']

        if Stops.objects.filter(point_name=point_name,stop_no=stop_no,charge=charges,ROUTE=route,lattitude=latitude,longitude=longitude).exists():
            return HttpResponse(f"<script>alert('Stop already exists');window.location='/admin_manage_stops/{id}'</script>")
        q = Stops(point_name=point_name,stop_no=stop_no,charge=charges,ROUTE=route,lattitude=latitude,longitude=longitude)
        q.save()
        return HttpResponse(f"<script>alert(' Stops Added ');window.location='/admin_manage_stops/{id}'</script>")
    return render(request,'admin/admin_manage_stops.html',{'q1':q1})


def update_stops(request,id):
    q2 = Stops.objects.get(id=id)
    if 'submit' in request.POST:
        point_name = request.POST['point_name']
        stop_no = request.POST['stop_no']
        charges = request.POST['charges']
        q2.point_name=point_name
        q2.stop_no=stop_no
        q2.charge=charges
        q2.save()
        return HttpResponse(f"<script>alert(' Stops Updated ');window.location='/admin_manage_stops/{q2.ROUTE.id}'</script>")
    return render(request,'admin/admin_manage_stops.html',{'q2':q2})


def delete_stops(request,id):
    q = Stops.objects.get(id=id)
    q.delete()
    return HttpResponse(f"<script>alert(' Stops Deleted ');window.location='/admin_manage_stops/{q.ROUTE.id}'</script>")



def admin_view_bus(request,id):
    q2 = Bus.objects.filter(OWER_id=id)
    return render(request,'admin/admin_view_bus.html',{'q2':q2})




def block(request,id):
    q = Bus.objects.get(OWER_id=request.session['oid'],id=id)
    print(q,"-----------------------")
    q.status='blocked'
    q.save()
    return HttpResponse(f"<script>alert('bus blocked');window.location='/admin_view_bus/{request.session['oid']}'</script>")


def unblock(request,id):
    q = Bus.objects.get(OWER_id=request.session['oid'],id=id)
    print(q,"-----------------------")
    q.status='Active'
    q.save()
    return HttpResponse(f"<script>alert('bus Active');window.location='/admin_view_bus/{request.session['oid']}'</script>")



def admin_view_feedback(request):
    return render(request,'admin/admin_view_feedback.html')

def admin_view_rating(request):
    return render(request,'admin/admin_view_rating.html')


# -------------------------------------------------------------------------------------------------------owner

def owner_home(request):
    return render(request,'owner/owner_home.html')



def owner_manage_bus(request):
    owner_id = request.session['oid']
    buses = Bus.objects.filter(OWER_id=owner_id,status = 'Active')

    if 'submit' in request.POST:
        bus_number = request.POST['bus_number']
        bus_name = request.POST['bus_name']
        
        s = Bus(bus_number=bus_number, bus_name=bus_name, OWER_id=owner_id)
        s.save()
        return HttpResponse("<script>alert('Bus Added');window.location='/owner_manage_bus'</script>")

    return render(request, 'owner/owner_manage_bus.html', {'q': buses})


def update_bus(request, id):
    try:
        bus = Bus.objects.get(id=id)
    except Bus.DoesNotExist:
        return HttpResponse("<script>alert('Bus Not Found');window.location='/owner_manage_bus'</script>")

    if request.method == 'POST':
        bus_number = request.POST.get('bus_number')
        bus_name = request.POST.get('bus_name')
        bus.bus_number = bus_number
        bus.bus_name = bus_name
        bus.save()
        return HttpResponse("<script>alert('Bus Updated');window.location='/owner_manage_bus'</script>")

    return render(request, 'owner/owner_manage_bus.html', {'q2': bus})



def delete_bus(request, id):
    try:
        bus = Bus.objects.get(id=id)
        bus.delete()
        return HttpResponse("<script>alert('Bus Deleted');window.location='/owner_manage_bus'</script>")
    except Bus.DoesNotExist:
        return HttpResponse("<script>alert('Bus Not Found');window.location='/owner_manage_bus'</script>")
    


def allocate_bus(request,id):
    qa = Allocation.objects.filter(BUS_id__status='Active')
    print(qa,'////////////////////////////////')
    route = Route.objects.all()
    driver = Driver.objects.all()
    oid = request.session['oid']
    if 'submit' in request.POST:
        route = request.POST['route']
        driver = request.POST['driver']
        a = Allocation(BUS_id=id,DRIVER_id=driver,OWER_id=oid,ROUTE_id=route)
        a.save()
        return HttpResponse(f"<script>alert(' Bus Allocated ');window.location='/allocate_bus/{id}'</script>")
    return render(request, 'owner/allocate_bus.html',{'q':route,'q1':driver,'qa':qa})
    


def get_stops(request, route_id):
    stops = Stops.objects.filter(ROUTE_id=route_id).values_list('point_name', flat=True)
    return JsonResponse({'stops': list(stops)})


def delete_allocate(request,id):
    q = Allocation.objects.all()
    q.delete()
    return HttpResponse(f"<script>alert(' Delete Allocated Bus');window.location='/allocate_bus/{id}'</script>")


def owner_manage_trip(request, id):
    owner_id = request.session['oid']
    q1 = Trip.objects.filter(ALLOCATION__OWER_id=owner_id,ALLOCATION_id = id)
    
    if 'submit' in request.POST:  
        start_time = request.POST['start_time']
        End_time = request.POST['End_time']
        
        # Check if the trip already exists
        existing_trip = Trip.objects.filter(
            ALLOCATION__OWER_id=owner_id,
            start_time=start_time,
            End_time=End_time
        ).exists()
        
        if existing_trip:
            # JavaScript to alert and reload the page after 3 minutes
            return HttpResponse("""
                <script>
                    alert('Trip already exists');
                    setTimeout(function() {
                        window.location.reload();
                    }, 180000); // 3 minutes delay
                </script>
            """)   
        
        q = Trip(start_time=start_time, End_time=End_time, ALLOCATION_id=id,status = "pending")
        q.save()
        return HttpResponse(f"<script>alert('Trip successfully added');window.location='/owner_manage_trip/{id}'</script>")
    
    return render(request, 'owner/owner_manage_trip.html', {'q1': q1})


def delete_trip(request,id):
    q = Trip.objects.get(id=id)
    q.delete()
    return HttpResponse(f"<script>alert(' trip Deleted');window.location='/owner_manage_trip/{id}'</script>")

def track(request):
    return render(request,'owner/track.html')












# -------------------------------ANDRIOD-------------------------











def login_and(request):
    username = request.POST['username']
    password = request.POST['password']
    
    print(f"Received login attempt for username: {username}")
    
    if Login.objects.filter(username=username, password=password).exists():
        qa = Login.objects.get(username=username, password=password)
        lid = qa.pk
        print(f"Login successful for user ID: {lid} with usertype: {qa.usertype}")
        
        if qa.usertype == 'driver':
            try:
                qc = Driver.objects.get(LOGIN_id=lid)
                print(f"Driver found: {qc}")
                
                did = qc.pk
                print(did,"driver id")

                bu = Allocation.objects.get(BUS_id=did)
                print(f"Allocation found: {bu}")
                
                return JsonResponse({'status': 'ok', 'lid': lid, 'did': did, 'bid': bu.id, 'usertype': 'driver'})
            except Driver.DoesNotExist:
                print("Driver does not exist.")
                return JsonResponse({'status': 'no'})
            except Allocation.DoesNotExist:
                print("Allocation does not exist.")
                return JsonResponse({'status': 'no'})
        
        elif qa.usertype == 'passenger':
            try:
                qd = Passenger.objects.get(LOGIN_id=lid)
                print(f"Passenger found: {qd}")
                pid = qd.pk
                return JsonResponse({'status': 'ok', 'lid': lid, 'pid': pid, 'usertype': 'passenger'})
            except Passenger.DoesNotExist:
                print("Passenger does not exist.")
                return JsonResponse({'status': 'no'})
        
        else:
            print("Invalid usertype.")
            return JsonResponse({'status': 'no'})
    else:
        print("Login failed.")
        return JsonResponse({'status': 'no'})
    



def passenger_registration(request):
    pname = request.POST['pname']
    place = request.POST['place']
    phone = request.POST['phone']
    post = request.POST['post']
    pincode = request.POST['pincode']
    city = request.POST['city']
    district = request.POST['district']

    photo = request.FILES['photo']
    fs=FileSystemStorage()
    vv=fs.save(photo.name,photo)

    username = request.POST['username']
    password = request.POST['password']
    m = Login(username=username,password=password,usertype = 'passenger')
    m.save()
    print(m,"Login_id")
    m1 = Passenger(pname=pname,place=place,phone=phone,post=post,pincode=pincode,city=city,district=district,photo=photo,LOGIN_id=m.pk)
    m1.save()
    return JsonResponse({'status':'no'}) 


def view_bus(request):
    id = request.POST['pid']
    a = Trip.objects.filter()
    b=[]
    for i in a:
        b.append({'id':id,'bus_name':i.ALLOCATION.BUS.bus_name,'bus_number':i.ALLOCATION.BUS.bus_number,'from_time':i.start_time,'end_time':i.End_time,'bid':i.ALLOCATION.BUS_id,'from_route':i.ALLOCATION.ROUTE.routes_start,'end_route':i.ALLOCATION.ROUTE.routes_End})
    return JsonResponse({'status':'ok',"q":b})



import datetime
from django.core import serializers

def psend_complaints(request):
   
    id = request.POST['pid']
    print(id,'-p_id- ')
    complaint = request.POST['complaint']
    date = datetime.date.today()
    bid = request.POST['bid']
    print(bid,'-bus_id-')
    if bid:
        pc = Complaints(complaint=complaint, reply='pending', PASSENGER_id=id, BUS_id=bid, date=date)
        pc.save()
        serialized_data = serializers.serialize('json', [pc])
        return JsonResponse({'status': 'ok', 'data': serialized_data})
    else:
        return JsonResponse({'status': 'error', 'message': 'Bus not found'}, status=400)
    

def view_reply(request):
    id = request.POST['pid']
    cv =Complaints.objects.filter(PASSENGER_id=id)
    a=[]
    for i in cv:
        a.append({'id':id,'complaints':i.complaint,'reply':i.reply,'date':i.date})
    return JsonResponse({'status':'ok','q':a})


def psend_feedback(request):
    id = request.POST['pid'] 
    print(id,'-p_id- ')
    feedback = request.POST['feedback']
    date = datetime.date.today()
    pc = Feedback(complaint=feedback, reply='pending', PASSENGER_id=id, date=date)
    pc.save()
    serialized_data = serializers.serialize('json', [pc])
    return JsonResponse({'status': 'ok', 'data': serialized_data})


def and_view_route_search(request):
    # Extract search parameters from request GET
    start_route = request.POST.get('from_route')
    end_route = request.POST.get('end_route')

    print(start_route,"to" ,end_route)
    # Filter Trip objects based on the search parameters
    trips = Trip.objects.filter(
        ALLOCATION__ROUTE__routes_start=start_route,
        ALLOCATION__ROUTE__routes_End=end_route
    )
    
    # Prepare the response data
    response_data = []
    for trip in trips:
        response_data.append({
            'id': trip.id,
            'bus_name': trip.ALLOCATION.BUS.bus_name,
            'bus_number': trip.ALLOCATION.BUS.bus_number,
            'from_time': trip.start_time,
            'end_time': trip.End_time,
            'bid': trip.ALLOCATION.BUS_id,
            'from_route': trip.ALLOCATION.ROUTE.routes_start,
            'end_route': trip.ALLOCATION.ROUTE.routes_End
        })

    return JsonResponse({'status': 'ok', 'q': response_data})





# ------------------------------driver------------------------------



def update_pass_location(request):
    did = request.POST['did']
    date = datetime.date.today()
    time =  datetime.date.today()
    lattitude = request.POST['lati']
    longitude = request.POST['longi']
    # place = request.POST['location']
    pc = Track(time=time,lattitude=lattitude, longitude=longitude, place="pp",date=date,DRIVER_id=did)
    pc.save()
    serialized_data = serializers.serialize('json', [pc])
    return JsonResponse({'status': 'ok', 'data': serialized_data})


def driver_update_status(request):
    id = request.POST['bid'] 
    # print(id,'-p_id- ')
    status = request.POST['status']
    date = datetime.date.today()
    pc = Bus_status(status=status,BUS_id=id, date=date)
    pc.save()
    serialized_data = serializers.serialize('json', [pc])
    return JsonResponse({'status': 'ok', 'data': serialized_data})

    



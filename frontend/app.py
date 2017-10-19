import sys
from flask import Flask, request, render_template, flash, redirect, url_for, session, logging
from data import Individual
from flask_mysqldb import MySQL
from wtforms import Form, StringField, SelectField, TextAreaField, PasswordField, validators
from passlib.hash import sha256_crypt
from functools import wraps
from flask_table import Table, Col
from jinja2 import Template

app = Flask(__name__)

userdatadict = {}

# Define Construction Phase Table


class ConstructionPhaseTable(Table):
    id = Col('id')
    phasename = Col('phasename')
    masterreferenceid = Col('masterreferenceid')
    constructionphasenumber = Col('constructionphasenumber')
    status = Col('status')
    startdate = Col('enddate')
    enddate = Col('enddate')
    constructioncost = Col('constructioncost')
    revisedconstructioncost = Col('revisedconstructioncost')

# Config MySQL


app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'barun'
app.config['MYSQL_DB'] = 'constructionloanmanagement'
# init MYSQL
mysql = MySQL(app)

Individual = Individual()

Individual_Type_Choice = [
    ('Admin', 'Admin'), ('Inspector', 'Inspector'), ('BankRep', 'BankRep')]

Phase_Status = [
    ('In Progress', 'In Progress'), ('Completed', 'Completed'), ('Not Started', 'Not Started')]


@app.context_processor
def phasestatustag():
    return dict(phasestatusitems=Phase_Status)


@app.route('/')
def index():
    return render_template('home.html')


@app.route('/dashboard')
def dashboard():
    if 'logged_in' in session:
        return render_template('dashboard.html')
    else:
        return redirect(url_for('login'))


@app.route('/about')
def about():
    return render_template('about.html')


@app.route('/individual')
def individual():
    return render_template('individual.html', individual=Individual)


@app.route('/article/<string:role_type>/')
def article(role_type):
    return render_template('article.html', role_type=role_type)
# User Register


class RegisterForm(Form):
    role_type = SelectField('Individual Type', choices=Individual_Type_Choice)
    firstname = StringField('FirstName', [validators.Length(min=1, max=50)])
    lastname = StringField('LastName', [validators.Length(min=1, max=50)])
    username = StringField('Username', [validators.Length(min=4, max=25)])
    email = StringField('Email', [validators.Length(min=6, max=50)])
    password = PasswordField('Password', [
        validators.DataRequired(),
        validators.EqualTo('confirm',  message='Password does not match')
    ])
    confirm = PasswordField('Confirm Password')


class AddressForm(Form):
    name = StringField('name', [validators.Length(min=1, max=45)])
    street = StringField('street', [validators.Length(min=4, max=100)])
    zip_code = StringField('zip_code', [validators.Length(min=4, max=20)])
    city = StringField('city', [validators.Length(min=3, max=30)])
    country_code = StringField(
        'country_code', [validators.Length(min=3, max=10)])
    latitude = StringField('latitude')
    longitude = StringField('longitude')


@app.route('/address', methods=['GET', 'POST'])
def address():
    if 'logged_in' in session:
        cur = mysql.connection.cursor()
        result = cur.execute("SELECT * FROM  address")
        if result > 0:
            addressitems = cur.fetchall()
            return render_template('address.html', addressitems=addressitems)
    else:
        return redirect(url_for('login'))


@app.route('/address/<string:indexid>/', methods=['GET', 'POST'])
def editaddress(indexid):
    if 'logged_in' in session:
        form = AddressForm(request.form)
        cur = mysql.connection.cursor()
        addressid = int(indexid)
        if request.method == 'GET':
            result = cur.execute(
                "SELECT * FROM  address where id = %s", [addressid])
            if result > 0:
                addressitem = cur.fetchone()
                form.name.data = addressitem[1]
                form.street.data = addressitem[2]
                form.zip_code.data = addressitem[3]
                form.city.data = addressitem[4]
                form.country_code.data = addressitem[5]
                form.latitude.data = addressitem[6]
                form.longitude.data = addressitem[7]
                cur.close()
                return render_template('editaddress.html', form=form, indexid=indexid)
            else:
                cur.close()
        else:
            if form.validate():
                name = form.name.data
                street = form.street.data
                zip_code = form.zip_code.data
                city = form.city.data
                country_code = form.country_code.data
                latitude = form.latitude.data
                if latitude in ("", "None"):
                    latitude = None
                longitude = form.longitude.data
                if longitude in ("", "None"):
                    longitude = None
                # create curosr
                cur.execute("""UPDATE address SET name=%s, street=%s, zip_code=%s, city=%s, country_code=%s, latitude=%s, longitude=%s WHERE id=%s
                """, (name, street, zip_code, city, country_code, latitude, longitude, addressid))
                mysql.connection.commit()
                # Close cursor connection
                cur.close()
                flash('Adreess are added successfully', 'Success')
                return redirect(url_for('address'))
            else:
                cur.close()
            return redirect(url_for('address'))
    else:
        return redirect(url_for('login'))


@app.route('/addnewaddress', methods=['GET', 'POST'])
def addnewaddress():
    if 'logged_in' in session:
        form = AddressForm(request.form)
        if request.method == 'POST':
            if form.validate():
                name = form.name.data
                street = form.street.data
                zip_code = form.zip_code.data
                city = form.city.data
                country_code = form.country_code.data
                latitude = form.latitude.data
                if latitude in ("", "None"):
                    latitude = None
                longitude = form.longitude.data
                if longitude in ("", "None"):
                    longitude = None
                # create curosr
                cur = mysql.connection.cursor()
                cur.execute("INSERT INTO address(name, street, zip_code, city, country_code, latitude, longitude) VALUES(%s, %s, %s, %s, %s, %s, %s)",
                            (name, street, zip_code, city, country_code, latitude, longitude))
                mysql.connection.commit()
                # Close cursor connection
                cur.close()
                flash('Adreess are added successfully', 'Success')
                return redirect(url_for('address'))
        else:
            return render_template('addnewaddress.html', form=form)
    else:
        return redirect(url_for('login'))


@app.route('/register', methods=['GET', 'POST'])
def register():
    form = RegisterForm(request.form)
    if request.method == 'POST' and form.validate():
        role_type = dict(Individual_Type_Choice).get(form.role_type.data)
        firstname = form.firstname.data
        lastname = form.lastname.data
        email = form.email.data
        username = form.username.data
        password = sha256_crypt.encrypt(str(form.password.data))

        # create curosr
        cur = mysql.connection.cursor()
        cur.execute("INSERT INTO individual(role_type, firstname, lastname, email, username, password) VALUES(%s, %s, %s, %s, %s, %s)",
                    (role_type, firstname, lastname, email, username, password))
        mysql.connection.commit()

        # Close cursor connection
        cur.close()

        flash('You are now registered can Log in Next', 'Success')
        return redirect(url_for('login'))
    return render_template('register.html', form=form)

# User Login


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        # Get Form Filled
        username = request.form['username']
        password_candidate = request.form['password']

        # create Cursor
        cur = mysql.connection.cursor()
        result = cur.execute(
            "SELECT * FROM  individual WHERE username = %s", [username])
        if result > 0:
            # Get Stored Hash
            userdata = cur.fetchone()
            password = userdata[13]
            global userdatadict
            userdatadict['userdata'] = userdata

            # compare password
            if sha256_crypt.verify(password_candidate, password):
                # Passed
                session['logged_in'] = True
                session['username'] = username
                flash('Password Matched, You are logged in', 'success')
                cur.close()
                return redirect(url_for('loginsession'))
            else:
                session.clear()
                cur.close()
                error = 'Invalid Login'
                return render_template('Password Not Matched', error=error)
        else:
            session.clear()
            cur.close()
            error = 'Username Not Found'
            return render_template('login.html', error=error)

    return render_template('login.html')

# Check if User Log in


def is_logged_in(f):
    @wraps(f)
    def wrap(*args, **kwargs):
        if 'logged_in' in session:
            return f(*args, **kwargs)
        else:
            flash('Unauthorized, Please login again', 'danger')
            return redirect(url_for('login'))
    return wrap

# Log out


@app.route('/logout')
@is_logged_in
def logout():
    userdatadict.clear()
    session.clear()
    flash('You are Now Logged Out ', 'success')
    return redirect(url_for('login'))

# Dashboard


@app.route('/loginsession')
@is_logged_in
def loginsession():
    return render_template('loginsession.html')


@app.route('/user', methods=['GET', 'POST'])
@is_logged_in
def user():
    if 'logged_in' in session:
        print("request.method = ", request.method, file=sys.stderr)
        if request.method == 'POST':
            cur = mysql.connection.cursor()
            try:
                # Get Form Filled
                print("request.form= ", request.form, file=sys.stderr)
                username = session['username']
                print("username = ", username, file=sys.stderr)
                company = request.form['company']
                print("company = ", company, file=sys.stderr)
                email = request.form['email']
                print("email = ", email, file=sys.stderr)
                firstname = request.form['firstname']
                print("firstname = ", firstname, file=sys.stderr)
                lastname = request.form['lastname']
                print("lastname = ", lastname, file=sys.stderr)
                street = request.form['street']
                print("street = ", street, file=sys.stderr)
                handphone = request.form['handphone']
                deskphone = request.form['deskphone']
                city = request.form['city']
                country_code = request.form['country_code']
                print("country_code = ", country_code, file=sys.stderr)
                zip_code = request.form['zip_code']
                aboutme = request.form['aboutme']

                # create curosr
                cur.execute("""UPDATE individual SET firstname=%s, lastname=%s, email=%s, company=%s, handphone=%s, deskphone=%s, street=%s, zip_code=%s, city=%s, country_code=%s, aboutme=%s WHERE username=%s
                """, (firstname, lastname, email, company, handphone, deskphone, street, zip_code, city, country_code, aboutme, username))
                mysql.connection.commit()

                result = cur.execute(
                    "SELECT * FROM  individual WHERE username = %s", [username])
                global userdatadict
                if result > 0:
                    # Get Stored Hash
                    userdatadict['userdata'] = cur.fetchone()
                cur.close()
                return render_template('user.html', userdatadict=userdatadict)
            except ValueError as err:
                print(err)
            finally:
                # Close cursor connection
                cur.close()
            flash('User Update carried Successfully', 'Success')
            return redirect(url_for('user'))
        else:
            return render_template('user.html', userdatadict=userdatadict)
    else:
        return redirect(url_for('login'))


@app.route('/table')
def table():
    if 'logged_in' in session:
        return render_template('table.html')
    else:
        return redirect(url_for('login'))


@app.route('/constructionloantracker')
@is_logged_in
def constructionloantracker():
    if 'logged_in' in session:
        # Create cursor
        cur = mysql.connection.cursor()

        # Get articles
        result = cur.execute("SELECT * FROM guideline")

        loantrackers = cur.fetchall()
        cur.close()

        return render_template('constructionloantracker.html', loantrackers=loantrackers)
    else:
        return redirect(url_for('login'))


@app.route('/createnewconstruction', methods=['GET', 'POST'])
@is_logged_in
def createnewconstruction():
    if 'logged_in' in session:
        print("request.method = ", request.method, file=sys.stderr)
        return render_template('createnewconstruction.html')
    else:
        return redirect(url_for('login'))


@app.route('/addnewconstructionphase/<string:projectid>/', methods=['GET', 'POST'])
@is_logged_in
def addnewconstructionphase(projectid):
    # create curosr
    cur = mysql.connection.cursor()
    print("request.method = ", request.method, file=sys.stderr)
    if request.method == 'POST':
        # Get Form Filled
        print("request.form= ", request.form, file=sys.stderr)
        phasename = request.form['projectname']
        print("phasename = ", phasename, file=sys.stderr)
        masterreferenceid = request.form['projectid']
        print("masterreferenceid = ", masterreferenceid, file=sys.stderr)
        phasenumber = request.form['phasenumber']
        print("phasenumber = ", phasenumber, file=sys.stderr)
        phasestatus = request.form['phase_select']
        print("Phasestatus = ", phasestatus, file=sys.stderr)
        phasestartdate = request.form['phasestartdate']
        print("phasestartdate = ", phasestartdate, file=sys.stderr)
        phaseenddate = request.form['phaseenddate']
        print("phaseenddate = ", phaseenddate, file=sys.stderr)
        constructioncost = request.form['constructioncost']
        print("constructioncost = ", constructioncost, file=sys.stderr)
        revisedconstructioncost = request.form['revisedconstructioncost']
        if revisedconstructioncost == "":
            revisedconstructioncost = None
        print("revisedconstrictioncost= ",
              revisedconstructioncost, file=sys.stderr)

        # create curosr
        cur.execute("INSERT INTO constructionphase(phasename, masterreferenceid, constructionphasenumber, status, startdate, enddate, constructioncost, revisedconstructioncost) VALUES(%s, %s, %s, %s, %s, %s, %s, %s)",
                    (phasename, masterreferenceid, phasenumber, phasestatus, phasestartdate, phaseenddate, constructioncost, revisedconstructioncost))
        mysql.connection.commit()
        return redirect(url_for('loantracker', projectid=projectid))
    else:
        try:
            # Query from MasterGuideline
            phaseid = int(projectid)
            result = cur.execute(
                "SELECT * FROM constructionphase WHERE masterreferenceid = %s", [phaseid])
            if result > 0:
                addnewphase = cur.fetchone()
                cur.execute(
                    "SELECT COUNT(*) FROM constructionphase WHERE masterreferenceid = %s", [phaseid])
                total_num_row = cur.fetchone()
                rowcount = int(total_num_row[0]) + 1
                cur.close()
                return render_template('addnewconstructionphase.html', addnewphase=addnewphase, rowcount=rowcount)
        except ValueError as err:
            print(err)
        finally:
            # Close cursor connection
            cur.close()
        return redirect(url_for('constructionloantracker'))


@app.route('/loantrack/<string:projectid>/', methods=['GET', 'POST'])
@is_logged_in
def loantracker(projectid):
        # create curosr
    cur = mysql.connection.cursor()
    try:
            # Query from MasterGuideline
        masterid = int(projectid)
        result = cur.execute(
            "SELECT * FROM guideline WHERE id = %s", [masterid])
        if result > 0:
            guideline = cur.fetchone()
            addressid = int(guideline[1])
            # Query from Address Phase Table
            result = cur.execute(
                "SELECT * FROM address WHERE id = %s", [addressid])
            address = cur.fetchone()
            # Query from Construction Phase Table
            result = cur.execute(
                "SELECT * FROM constructionphase WHERE masterreferenceid = %s", [masterid])
            constructionphasesitems = cur.fetchall()
            cur.close()
            return render_template('loantrack.html', projectid=projectid, guideline=guideline, address=address, constructionphasesitems=constructionphasesitems)
    except ValueError as err:
        print(err)
    finally:
        # Close cursor connection
        cur.close()
    return redirect(url_for('constructionloantracker'))


@app.route('/loantrack/<string:projectid>/<string:phaseid>', methods=['GET', 'POST'])
@is_logged_in
def editloantracker(projectid, phaseid):
    cur = mysql.connection.cursor()
    masterid = int(projectid)
    phaseid = int(phaseid)
    if request.method == 'GET':
        # create curosr
        try:
            # Query from MasterGuideline
            result = cur.execute(
                "SELECT * FROM constructionphase WHERE masterreferenceid = %s AND constructionphasenumber = %s", [masterid, phaseid])
            if result > 0:
                constructionphase = cur.fetchone()
                cur.close()
                return render_template('editconstructionphase.html', constructionphase=constructionphase)
        except ValueError as err:
            print(err)
        finally:
            # Close cursor connection
            cur.close()
        return redirect(url_for('constructionloantracker'))
    else:
         # Get Form Filled
        print("request.form= ", request.form, file=sys.stderr)
        phasestatus = request.form['phase_select']
        print("Phasestatus = ", phasestatus, file=sys.stderr)
        phasestartdate = request.form['phasestartdate']
        print("phasestartdate = ", phasestartdate, file=sys.stderr)
        phaseenddate = request.form['phaseenddate']
        print("phaseenddate = ", phaseenddate, file=sys.stderr)
        constructioncost = request.form['constructioncost']
        print("constructioncost = ", constructioncost, file=sys.stderr)
        revisedconstructioncost = request.form['revisedconstructioncost']
        if revisedconstructioncost in ("", "None"):
            revisedconstructioncost = None
        print("revisedconstrictioncost = ",
              revisedconstructioncost, file=sys.stderr)

        # create curosr
        cur.execute("""UPDATE constructionphase SET status=%s, startdate=%s, enddate=%s, constructioncost=%s, revisedconstructioncost=%s WHERE id=%s AND masterreferenceid=%s
        """, (phasestatus, phasestartdate, phaseenddate, constructioncost, revisedconstructioncost, phaseid, masterid))
        mysql.connection.commit()
        return redirect(url_for('loantracker', projectid=projectid))


@app.route('/icons')
def icons():
    if 'logged_in' in session:
        return render_template('icons.html')
    else:
        return redirect(url_for('login'))


@app.route('/maps')
def maps():
    if 'logged_in' in session:
        return render_template('maps.html')
    else:
        return redirect(url_for('login'))


@app.route('/notifications')
def notifications():
    if 'logged_in' in session:
        return render_template('notifications.html')
    else:
        return redirect(url_for('login'))


@app.route('/upgrade')
def upgrade():
    return render_template('upgrade.html')


if __name__ == '__main__':
    app.secret_key = 'secret123'
    app.run(debug=True)

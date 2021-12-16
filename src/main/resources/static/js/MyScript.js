const url = 'http://localhost:8080/api/users'
const allUsers = document.getElementById('allUsers')
const getEditBtn = document.getElementById('editBtn')
const getDeleteBtn = document.getElementById('deleteBtn')
const getNewUserBtn = document.getElementById('newUserBtn')

const idEdit = document.getElementById('idE')
const firstNameEdit = document.getElementById('firstNameE')
const lastNameEdit = document.getElementById('lastNameE')
const ageEdit = document.getElementById('ageE')
const emailEdit = document.getElementById('emailE')
const passwordEdit = document.getElementById('passwordE')
const rolesEdit = document.getElementById('rolesE')

let output

const user = {
    id: '',
    firstName: '',
    lastName: '',
    age: '',
    email: '',
    password: '',
    roles: []
}
function fillTheTable() {
    fetch(url,)
        .then(res => res.json())
        .then((data) => {
            output = ''
            data.forEach(post => {
                output += `
            <tr>
                <td id="id${post.id}">${post.id}</td>
                <td id="firstName{post.id}">${post.firstName}</td>
                <td id="lastName{post.id}">${post.lastName}</td>
                <td id="age{post.id}">${post.age}</td>
                <td id="email{post.id}">${post.email}</td>
                <td id="roles${post.id}">${post.roles.map(r => r.role).join(', ')}</td>
                <td>
                    <button type="button" class="btn btn-info" data-toggle="modal"
                            data-target="#edit" onclick="edit(${post.id})">
                        Edit
                    </button>
                 </td>
                 <td>
                    <button type="button" class="btn btn-danger" data-toggle="modal"
                            data-target="#delete" onclick="del(${post.id})">
                        Delete
                    </button>
                 </td>
            </tr>             
`
            })
            allUsers.innerHTML = output
        })
}
fillTheTable()


function edit(id){
    fetch(`${url}/${id}`)
        .then(res => res.json())
        .then(user => {
                idEdit.setAttribute('value',user.id);
                firstNameEdit.setAttribute('value',user.firstName);
                lastNameEdit.setAttribute('value',user.lastName);
                ageEdit.setAttribute('value',user.age);
                emailEdit.setAttribute('value',user.email);
                passwordEdit.setAttribute('value',user.password);
            }
        )
}

function del(id){
    fetch(`${url}/${id}`)
        .then(res => res.json())
        .then(user => {
            document.getElementById('idD').setAttribute('value',user.id);
            document.getElementById('firstNameD').setAttribute('value',user.firstName);
            document.getElementById('lastNameD').setAttribute('value',user.lastName);
            document.getElementById('ageD').setAttribute('value',user.age);
            document.getElementById('emailD').setAttribute('value',user.email);
            }
        )
}

getEditBtn.addEventListener('click', async (e) =>{
    e.preventDefault()
    await getPutRequest(document.getElementById('idE').value)
    $('#edit').hide()
    $(".modal-backdrop").remove()
    fillTheTable()
})

function getPutRequest(){
    user.id = idEdit.getAttribute('value')
    user.firstName = firstNameEdit.value
    user.lastName = lastNameEdit.value
    user.age = ageEdit.value
    user.email = emailEdit.value
    user.roles = Array.from(rolesEdit.options)
            .filter(option => option.selected)
            .map(option => option.value)
    return fetch(url,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(user)
    })
}

getDeleteBtn.addEventListener('click', async (e) =>{
    e.preventDefault()
    await getDeleteRequest(document.getElementById('idD').value)
    $('#delete').hide()
    $(".modal-backdrop").remove()
    fillTheTable()
})

function getDeleteRequest(id){
    return fetch(`${url}/${id}`,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        }
    })
}

getNewUserBtn.addEventListener('click', async(e) => {
    e.preventDefault()
    await getPostRequest()
    fillTheTable()
})

function getPostRequest(){
    user.id = ''
    user.firstName = document.getElementById('firstNameN').value
    user.lastName = document.getElementById('lastNameN').value
    user.age = document.getElementById('ageN').value
    user.email = document.getElementById('emailN').value
    user.password = document.getElementById('passwordN').value
    user.roles = Array.from(document.getElementById('rolesN').options)
        .filter(option => option.selected)
        .map(option => option.value)
    console.log(user)
    document.getElementById('firstNameN').value = ''
    document.getElementById('lastNameN').value = ''
    document.getElementById('ageN').value =''
    document.getElementById('passwordN').value = ''
    document.getElementById('emailN').value=''
    return fetch(url,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(user)
    })

}








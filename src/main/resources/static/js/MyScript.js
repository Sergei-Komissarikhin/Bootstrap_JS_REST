const url = 'http://localhost:8080/api/users'
const allUsers = document.getElementById('allUsers')
const getEditBtn = document.getElementById('editBtn')
let output = ''

const idEdit = document.getElementById('idE')
const firstNameEdit = document.getElementById('firstNameE')
const lastNameEdit = document.getElementById('lastNameE')
const ageEdit = document.getElementById('ageE')
const emailEdit = document.getElementById('emailE')
const rolesEdit = document.getElementById('rolesE')

const user = {
    id: '',
    firstName: '',
    lastName: '',
    age: '',
    email: '',
    password: '',
    roles: []
}
    fetch(url,)
        .then(res => res.json())
        .then((data) => {
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


function edit(id){
    fetch(`${url}/${id}`)
        .then(res => res.json())
        .then(user => {
                document.getElementById('idE').setAttribute('value',user.id);
                document.getElementById('firstNameE').setAttribute('value',user.firstName);
                document.getElementById('lastNameE').setAttribute('value',user.lastName);
                document.getElementById('ageE').setAttribute('value',user.age);
                document.getElementById('emailE').setAttribute('value',user.email);
                document.getElementById('passwordE').setAttribute('value',user.password);
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
    await getPutRequest()

    $('#edit').hide()
    $(".modal-backdrop").remove()

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
    console.log(user)
    return fetch(url,{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(user)
    })
}








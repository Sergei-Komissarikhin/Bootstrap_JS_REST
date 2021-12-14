const url = 'http://localhost:8080/api/users'
const allUsers = document.getElementById('allUsers')
let output = ''
fetch(url)
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
                console.log(user)
            }
        )
}

document.getElementById('edit').addEventListener('submit', () => {
    console.log('Hello World from submit button')
})

let user = {
    firstName: 'Vlad',
    lastName: 'Petrov',
    age: 33,
    email: 'sus@mail.ru',
    password: '1234',
    roles: [
        {
            id: 2,
            role: 'USER'
        }
    ]
}

fetch(url,{
    method:'POST',
    headers: {
        'Content-Type' : 'application/json; charset=utf-8'
    },
    body: JSON.stringify('user')
})



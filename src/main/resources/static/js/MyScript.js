const url = 'http://localhost:8080/api/users'
console.log(url)
const allUsers = document.getElementById('allUsers')
let output = ''
fetch(url)
    .then(res => res.json())
    .then((data) => {
        data.forEach(post => {
            console.log(post.roles);
            output += `
            <tr>
                <td id="id${post.id}">${post.id}</td>
                <td id="firstName{post.id}">${post.firstName}</td>
                <td id="lastName{post.id}">${post.lastName}</td>
                <td id="age{post.id}">${post.age}</td>
                <td id="email{post.id}">${post.email}</td>
                <td id="roles${post.id}">${post.roles.map(r => r.role).join(', ')}</td>
                <td>
                                                <!-- Button edit modal -->
                    <button type="button" class="btn btn-info" data-toggle="modal"
                            data-target="#edit" onclick="edit(${post.id})"
                            th:attrappend="data-target=${post.id}">
                        Edit
                    </button>
                 </td>
                 <td>
                                                <!-- Button delete modal -->
                    <button type="button" class="btn btn-danger" data-toggle="modal"
                            data-target="#delete" onclick="del(${post.id})"
                            th:attrappend="data-target=${post.id}">
                        Delete
                    </button>
                 </td>
            </tr>             
`
        })
        allUsers.innerHTML = output

    }).then(data => console.log(data))

function edit(id){
    fetch(`${url}/${id}`)
        .then(res => res.json())
        .then(user => {
            console.log(user)
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
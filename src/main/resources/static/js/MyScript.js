const url ='http://localhost:8080/api/users'
console.log(url)
const allUsers = document.getElementById('allUsers')
let output = ''
fetch(url)
    .then(res => res.json())
    .then((data) =>{
    data.forEach(post => {
output += `
<tr>
                <td id="id${post.id}">${post.id}</td>
                <td id="firstName{post.id}">${post.firstName}</td>
                <td id="lastName{post.id}">${post.lastName}</td>
                <td id="age{post.id}">${post.age}</td>
                <td id="email{post.id}">${post.email}</td>
                <td id="roles${post.id}">${post.role}</td>
                </tr>             
`
    })
        allUsers.innerHTML = output

})
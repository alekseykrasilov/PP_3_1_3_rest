const urlUser = 'http://localhost:8080/api/user';
const urlLogin = 'http://localhost:8080/api/loginInfo'
const tableUser = document.querySelector('tbody');
const infoUsername = document.getElementById('infoUsername')
const infoRoles = document.getElementById('infoRoles')

//info for identity row
fetch(urlLogin)
    .then(response => response.json())
    .then(user => {
        const infoUsernameLogin = `${user.username}`
        const infoRolesLogin = 'with roles ' + `${user.roles.map(role => role.name)}`
        infoUsername.innerHTML = infoUsernameLogin
        infoRoles.innerHTML = infoRolesLogin
    })

let result = '';
const currentUser = (user) => {
    result += `
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.age}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.roles.map(role => role.name)}</td>
        </tr>`
    tableUser.innerHTML = result
}

fetch(urlUser)
    .then(response => response.json())
    .then(data => currentUser(data))
    .catch(error => console.log(error))


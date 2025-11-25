window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del odontólogo
    const formulario = document.querySelector('#update_dentist_form');

    formulario.addEventListener('submit', function (event) {
        let dentistId = document.querySelector('#dentist_id').value;

        //creamos un JSON que tendrá los datos del odontólogo
        //a diferencia de un odontólogo nuevo en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#dentist_id').value,
            registration: document.querySelector('#registration').value,
            name: document.querySelector('#name').value,
            lastName: document.querySelector('#lastName').value,

        };

        //invocamos utilizando la función fetch la API odontólogos con el método PUT que modificará
        //el odontólogo que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => {
              if (response.ok) {
                  //Si no hay ningun error se muestra un mensaje diciendo que el odontólogo
                  //se actualizo bien
                  let successAlert = '<div class="alert alert-success alert-dismissible">' +
                      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                      '<strong></strong> Odontólogo actualizado </div>'

                  document.querySelector('#response').innerHTML = successAlert;
                  document.querySelector('#response').style.display = "block";
                  //Ocultar el formulario de actualización
                  document.querySelector('#div_dentist_updating').style.display = "none";
                  //Recargar la página para mostrar los cambios
                  location.reload();
              } else {
                  throw new Error('Error al actualizar el odontólogo');
              }
          })
          .catch(error => {
              //Si hay algun error se muestra un mensaje diciendo que el odontólogo
              //no se pudo actualizar y se intente nuevamente
              let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                               '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                               '<strong> Error intente nuevamente</strong> </div>'

              document.querySelector('#response').innerHTML = errorAlert;
              document.querySelector('#response').style.display = "block";
          })

    })
 })

//Es la funcion que se invoca cuando se hace click sobre el id de un odontólogo del listado
//se encarga de llenar el formulario con los datos del odontólogo
//que se desea modificar
function findBy(id) {
          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let dentist = data;
              document.querySelector('#dentist_id').value = dentist.id;
              document.querySelector('#registration').value = dentist.registration;
              document.querySelector('#name').value = dentist.name;
              document.querySelector('#lastName').value = dentist.lastName;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_dentist_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }
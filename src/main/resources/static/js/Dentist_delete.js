function deleteBy(id)
{
          //con fetch invocamos a la API de odontólogos con el método DELETE
          //pasandole el id en la URL
          const url = '/odontologos/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => {
              if (response.ok) {
                  //Si la eliminación fue exitosa, borrar la fila del odontólogo eliminada
                  let row_id = "#tr_" + id;
                  document.querySelector(row_id).remove();
                  
                  //Mostrar mensaje de éxito
                  let successAlert = '<div class="alert alert-success alert-dismissible">' +
                      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                      '<strong>Odontólogo eliminado correctamente</strong> </div>';
                  
                  //Buscar un contenedor para mostrar el mensaje o crear uno temporal
                  let alertContainer = document.querySelector('#response') || document.body;
                  alertContainer.innerHTML = successAlert;
                  if (document.querySelector('#response')) {
                      document.querySelector('#response').style.display = "block";
                  }
              } else {
                  throw new Error('Error al eliminar el odontólogo');
              }
          })
          .catch(error => {
              //Si hay algun error se muestra un mensaje
              let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                               '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                               '<strong>Error al eliminar el odontólogo. Intente nuevamente.</strong> </div>';
              
              let alertContainer = document.querySelector('#response') || document.body;
              alertContainer.innerHTML = errorAlert;
              if (document.querySelector('#response')) {
                  document.querySelector('#response').style.display = "block";
              }
              console.error('Error:', error);
          });

}
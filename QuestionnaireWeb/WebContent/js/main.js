const form = document.getElementById('addForm');
var itemsList = document.getElementById('items');

form.addEventListener("submit", addItem);
itemsList.addEventListener('click', removeItem);



function addItem(e) {
    e.preventDefault();
    console.log(1);
    var newItem = document.getElementById("question").value;

    var div = document.createElement('div');
    div.className = "form-row";

    var col = document.createElement("div");
    col.className = "col-11";

    var col1 = document.createElement("div");
    col1.className = "col-1";

    var li = document.createElement('li');
    li.className = "list-group-item";

    div.appendChild(col);
    div.appendChild(col1);
    col.appendChild(li);
    li.appendChild(document.createTextNode(newItem));
    console.log(div);


    var deleteBtn = document.createElement('button');
    deleteBtn.className = 'deleteBtn';
    deleteBtn.appendChild(document.createTextNode('Delete'));
    col1.appendChild(deleteBtn);

    itemsList.appendChild(div);

}

function removeItem(e) {
    if (e.target.classList.contains('deleteBtn')) {
        if (confirm('Are you sure?')) {
            var row = e.target.parentElement.parentElement;
            itemsList.removeChild(row);
        }
    }




}
const form = document.getElementById('addForm');
var itemsList = document.getElementById('items');
document.getElementById('customFile').onchange = function () {
    var fullPath = $("#customFile").val();    
    var filename = fullPath.replace(/^.*[\\\/]/, '');  
    document.getElementById("label").innerHTML=filename;
    document.getElementById("imagePath").value=filename;
  };

form.addEventListener("submit", addItem);
itemsList.addEventListener('click', removeItem);



function addItem(e) {
    e.preventDefault();

    var inp = document.createElement("input");
    inp.setAttribute("type","hidden");
    inp.setAttribute("name","question");
    inp.setAttribute("value",document.getElementById("question").value);

    var newItem = document.getElementById("question");
    var div = document.createElement('div');
    div.className = "form-row";

    var col = document.createElement("div");
    col.className = "col-11";

    var col1 = document.createElement("div");
    col1.className = "col-1";

    var li = document.createElement('li');
    li.className = "list-group-item";
    li.setAttribute("name","li")

    div.appendChild(col);
    div.appendChild(col1);
    col.appendChild(li);
    col.appendChild(inp);
    li.appendChild(document.createTextNode(newItem.value));
    console.log(div);


    var deleteBtn = document.createElement('button');
    deleteBtn.className = 'deleteBtn';
    deleteBtn.appendChild(document.createTextNode('Delete'));
    col1.appendChild(deleteBtn);

    itemsList.appendChild(div);
    document.getElementById("question").value='';

    


}

function removeItem(e) {
    if (e.target.classList.contains('deleteBtn')) {
        if (confirm('Are you sure?')) {
            var row = e.target.parentElement.parentElement;
            itemsList.removeChild(row);

        }
    }




}
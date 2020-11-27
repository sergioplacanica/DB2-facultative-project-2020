const form = document.getElementById('addForm');
var itemsList=document.getElementById('items');
form.addEventListener("submit",addItem);
itemsList.addEventListener('click',removeItem);



function addItem(e){
    e.preventDefault();
    console.log(1);
    var newItem=document.getElementById("question").value;
    var li=document.createElement('li');
    li.className="list-group-item";
    console.log(li);
    li.appendChild(document.createTextNode(newItem));

    var deleteBtn= document.createElement('button');
    deleteBtn.className='btn btn-danger btn-sm float-right delete';
    deleteBtn.appendChild(document.createTextNode('X'));
    li.appendChild(deleteBtn);

    itemsList.appendChild(li);

}

function removeItem(e){
    if(e.target.classList.contains('delete')){
        if(confirm('Are you sure?')){
            var li=e.target.parentElement;
            itemsList.removeChild(li);
        }
    }




}
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function verifQte(f)
{
    var qte = parseInt(f.qte.value);
    if (isNaN(qte) || qte < 1) {
        surligne(f.qte, true);
        alert("Veuillez saisir la quantité (>0)");
        return false;
    } else
        return true;
}

function surligne(champ, erreur)
{
    if (erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}
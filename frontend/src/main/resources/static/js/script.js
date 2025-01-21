document.addEventListener('DOMContentLoaded', function() {
    let selectedRace = null;
    let selectedClass = null;
    let selectedSubrace = null;
    let selectedSubclass = null;

    function fetchSubraces(raceId) {
        fetch(`/get-subraces?raceId=${raceId}`)
            .then(response => response.text())
            .then(data => {
                document.getElementById("subrace-fragment").innerHTML = data;
            });
    }

    function fetchSubclasses(classId) {
        fetch(`/get-subclasses?classId=${classId}`)
            .then(response => response.text())
            .then(data => {
                document.getElementById("subclass-fragment").innerHTML = data;
            });
    }

    const raceButtons = document.querySelectorAll('.select-race-button');
    raceButtons.forEach(button => {
        button.addEventListener('click', function() {
            if (selectedRace) {
                selectedRace.classList.remove('selected');
            }
            this.classList.add('selected');
            selectedRace = this;
            selectedSubrace=null;
            const raceId = this.getAttribute('data-id');
            document.getElementById('selected-race').value = raceId;

            fetchSubraces(raceId);
        });
    });

    const classButtons = document.querySelectorAll('.select-class-button');
    classButtons.forEach(button => {
        button.addEventListener('click', function() {
            if (selectedClass) {
                selectedClass.classList.remove('selected');
            }
            this.classList.add('selected');
            selectedClass = this;
            selectedSubclass=null;
            const classId = this.getAttribute('data-id');
            document.getElementById('selected-class').value = classId;

            fetchSubclasses(classId);
        });
    });

    document.body.addEventListener('click', function(event) {
        if (event.target.classList.contains('select-subrace-button')) {
            if (selectedSubrace) {
                selectedSubrace.classList.remove('selected');
            }
            event.target.classList.add('selected');
            selectedSubrace = event.target;

            const subraceId = event.target.getAttribute('data-id');
            document.getElementById('selected-subrace').value = subraceId;
        }
    });

    document.body.addEventListener('click', function(event) {
        if (event.target.classList.contains('select-subclass-button')) {
            if (selectedSubclass) {
                selectedSubclass.classList.remove('selected');
            }
            event.target.classList.add('selected');
            selectedSubclass = event.target;

            const subclassId = event.target.getAttribute('data-id');
            document.getElementById('selected-subclass').value = subclassId;
        }
    });
});

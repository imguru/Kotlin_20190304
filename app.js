/*
function lookForAlice(people) {
	for (person of people) {
		if (person.name == "Alice") {
			console.log("Found");
			return;
		}
	}

	console.log("Not found");
}
*/

function lookForAlice(people) {
	people.forEach(person => {
		if (person.name == "Alice") {
			console.log("Found");
			return;
		}
	});

	console.log("Not found");
}

const list = [
	{
			name: "Tom",
	},
	{
			name: "Alice",
	}
];

lookForAlice(list);

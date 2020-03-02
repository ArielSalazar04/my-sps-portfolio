// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const quotes = ["/images/quote1.jpg", "/images/quote2.png", "/images/quote3.jpg"];
  document.getElementById("myImage").src = quotes[Math.floor(Math.random() * quotes.length)];
  // Picks a random quote and adds it to the page
}

function getValues() {
  fetch('/data').then(response => response.json()).then((msg) => {
      var messages = String(msg);
      messages = messages.split(",");
      const commentList = document.getElementById("demo");
      commentList.innerHTML = '';
      commentList.appendChild(
          createListElement('Value1: ' + messages[0]));
      commentList.appendChild(
          createListElement('Value2: ' + messages[1]));
      commentList.appendChild(
          createListElement('Value3: ' + messages[2]));
  });
}

/*function getValues() {
  fetch('/data').then(response => response.json()).then((messages) => {
    const commentList = document.getElementById('quote-container');
    commentList.innerHTML = '';
    commentList.appendChild(
        createListElement('Value1: ' + messages.get(0)));
    commentList.appendChild(
        createListElement('Value2: ' + messages.get(1)));
    commentList.appendChild(
        createListElement('Value3: ' + messages.get(2)));
  });
}*/

function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
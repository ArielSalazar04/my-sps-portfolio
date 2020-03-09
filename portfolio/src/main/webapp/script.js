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

function getComments(languageCode="en") {
    const params = new URLSearchParams();
    params.append('languageCode', languageCode);

    fetch("/data?" + params.toString()).then(response => response.json()).then((comments) => {
    const commentListElement = document.getElementById('list-of-comments');
    comments.forEach((singleComment) => {
        commentListElement.appendChild(newComment(singleComment));
    })
    });
}

function requestTranslation() {
    document.getElementById('list-of-comments').innerText = "";
    getComments(document.getElementById('language').value);
}

function newComment(comment) {
    const commentElement = document.createElement('li');
    commentElement.className = 'id';
    
    const textElement = document.createElement('one-comment');
    textElement.innerText = comment.text;

    commentElement.appendChild(textElement);
    return commentElement;
}

function createListElement(text) {
  const liElement = document.createElement("li");
  liElement.innerText = text;
  return liElement;
}